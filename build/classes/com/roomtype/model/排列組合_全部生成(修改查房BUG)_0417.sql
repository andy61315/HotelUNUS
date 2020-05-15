ALTER SESSION SET NLS_DATE_FORMAT = 'YYYY-MM-DD';
DROP  TABLE COMBINATION;
CREATE TABLE COMBINATION(--裝所有30人以下的房型/數量組合的表格
ONE_ number(2),
TWO_ number(2), 
THREE_ number(2),
FOUR_ number(2),
FIVE_ number(2),
SIX_ number(2),
MIN_ NUMBER(2),
MAX_ NUMBER(2),
EMPTY_COUNT NUMBER(2)
);

/--應該得把允許?不允許加床的分成兩個表格INPUT值
--把<=30人的排列組合資料都放進table
--生成COMBINATION的表格資料(未加床版)
CREATE OR REPLACE PROCEDURE ROOM_COMBINATION(ALLOW_ADD_BED IN VARCHAR2)--選設是否允許加床
AS
    CURSOR C_DIF_NO_ROOM IS --取得2/3/4等不同人數房型 的總數
        SELECT PERSON_CAPACITY AS PEOPLE, SUM(QUANTITY)AS SUM FROM ROOM_TYPE 
        GROUP BY PERSON_CAPACITY;
    TYPE DATA_TABLE IS TABLE OF NUMBER INDEX BY BINARY_INTEGER;--記錄所有不同人數房型的數量
    DATA_ARR DATA_TABLE;
    ARR_LENGTH NUMBER;
    OUTPUT DATA_TABLE; --要塞到TABLE的陣列
    TEST_STR VARCHAR2(30) := '';
    counter number(3) := 0;
    CUR_PEOPLE NUMBER(2) := 0;
    MIN_PEOPLE NUMBER(2):=0;--計算最少能住幾人
    EMPTY_NO NUMBER(2):=0;
BEGIN
        DELETE FROM COMBINATION;--先刪除原本的表格內容
        FOR i IN 1 .. 6 LOOP --設每個房型初始值為0
            DATA_ARR(i) := 0;
            OUTPUT(i)   := 0;
        END LOOP;
        FOR C IN C_DIF_NO_ROOM LOOP --把抓到的不同數量房型資料放進TABLE
            DATA_ARR(C.PEOPLE) := C.SUM;
        END LOOP;
        ARR_LENGTH:= DATA_ARR.COUNT;--陣列長度(6)
        
        LOOP
--            TEST_STR := '';
--            FOR i IN 1 .. OUTPUT.COUNT LOOP --測試印出
--            TEST_STR := TEST_STR ||' ' ||OUTPUT(i);
--            END LOOP;
--            DBMS_OUTPUT.PUT_LINE(TEST_STR||'   '||CUR_PEOPLE);
            
            IF OUTPUT(1) > DATA_ARR(1) THEN
                EXIT;
            END IF;
            OUTPUT(ARR_LENGTH) := OUTPUT(ARR_LENGTH) + 1;
            CUR_PEOPLE := CUR_PEOPLE + ARR_LENGTH; 

            FOR i IN REVERSE 2 .. ARR_LENGTH  LOOP
                IF ((OUTPUT(i) > DATA_ARR(i)) OR CUR_PEOPLE>30) THEN
--                  DBMS_OUTPUT.PUT_LINE('TEST'||'OUTPUT='||OUTPUT(i)||CUR_PEOPLE);
                    CUR_PEOPLE := CUR_PEOPLE - i*OUTPUT(i) + (i-1);
                    OUTPUT(i-1) := OUTPUT(i-1) + 1;
                    OUTPUT(i)     := 0;
                ELSE 
                    EXIT;
                END IF;
            
            END LOOP;
            IF CUR_PEOPLE <= 30 THEN
                MIN_PEOPLE:= 0;
                FOR i IN 1..6 LOOP
                    MIN_PEOPLE :=MIN_PEOPLE + OUTPUT(i); 
                    IF OUTPUT(i) = 0 THEN
                        EMPTY_NO := EMPTY_NO + 1;
                    END IF;
                END LOOP;
                INSERT INTO combination (
                    one_,  two_,  three_,  four_,  five_,  six_,  min_,  max_, EMPTY_COUNT) 
                VALUES (OUTPUT(1),OUTPUT(2),OUTPUT(3),OUTPUT(4),OUTPUT(5),OUTPUT(6),MIN_PEOPLE,CUR_PEOPLE, EMPTY_NO);
                EMPTY_NO := 0;
                TEST_STR := '';
                FOR i IN 1 .. OUTPUT.COUNT LOOP --測試印出
                TEST_STR := TEST_STR ||' ' ||OUTPUT(i);
                END LOOP;
--                DBMS_OUTPUT.PUT_LINE(TEST_STR||'   '||CUR_PEOPLE);
            END IF;
--            FOR i IN 1 .. OUTPUT.COUNT LOOP --測試印出
--                TEST_STR := TEST_STR ||' ' ||OUTPUT(i);
--                --算人數
--                
--                --算房間數
--            END LOOP;
--            DBMS_OUTPUT.PUT_LINE(TEST_STR);
            
        END LOOP;
        COMMIT;
END;
/
--呼叫ROOM_COMBINATION，生成資料
BEGIN--要寫成trigger
    ROOM_COMBINATION('');  

END;
/
DROP TYPE ROOM_RE_TAB;
/
DROP TYPE ROOM_RE_O;
/
CREATE OR REPLACE TYPE ROOM_RE_O as OBJECT(--存房型的剩餘數量的物件
        ROOM_TYPE_NO VARCHAR2(5 BYTE), ROOM_TYPE_NAME VARCHAR2(20 BYTE), REMAIN NUMBER(2,0),
        PERSON_CAPACITY NUMBER(1,0), TOTAL_PEOPLE NUMBER(2,0)
);
/
CREATE OR REPLACE  TYPE ROOM_RE_TAB IS TABLE OF ROOM_RE_O ;--"房型剩餘數量物件"的陣列

/
--------------------------------------------------------------------------------

--------------------------------------------------------------------------------
--運算單一房型
create or replace PROCEDURE ROOM_REMAIN_SINGLE (F_DATE IN VARCHAR2, L_DATE IN VARCHAR2, RT IN VARCHAR2 ,RETURN_TAB IN out ROOM_RE_TAB) 
AS
   --建TABLE，以字串當INDEX，不同的日期轉成字串設定成INDEX
    TYPE room_data IS TABLE OF NUMBER INDEX BY VARCHAR2(10);
    DATE_TABLE          room_data;
    first_date_str   VARCHAR2(10) ;
    last_date_str    VARCHAR2(10) ;
    RT_NO            VARCHAR2(5)  ;--房型編號
    max_QTY          NUMBER(2);--存這個房型的最大房間數量
    DATE_COUNT       VARCHAR2(10) ;
    index1           VARCHAR2(10);--用來跑回圈的INDEX
    THIS_DATE        DATE ;--把INDEX1字串內的日期轉成日期格式做比對
    CURSOR c_odr IS SELECT --建立CURSOR準備取得查詢資料
            bod.b_order_no   AS order_no,
            bom.start_date   AS start_date,
            bom.end_date     AS end_date,
            bod.quantity     AS quantity
        FROM
            booking_order_detail   bod
            JOIN booking_order_master   bom 
            ON bod.b_order_no = bom.b_order_no
        WHERE
            bod.room_type_no = RT
            AND ( ( bom.start_date >= first_date_str AND bom.start_date < last_date_str )
                 OR ( bom.end_date > first_date_str AND bom.end_date <= last_date_str ) );
    TYPE odr_r IS RECORD (--宣告RECORD裝每筆資料
        order_no     booking_order_master.b_order_no%TYPE,
        start_date   booking_order_master.start_date%TYPE,
        end_date     booking_order_master.end_date%TYPE,
        quantity     booking_order_detail.quantity%TYPE
    );
    my_odr   odr_r;
    TYPE ord_t IS TABLE OF odr_r INDEX BY BINARY_INTEGER; --存房號/日期/數量
    odrs     ord_t;
    i        INTEGER := 0;
    MIN_QTY INTEGER := 0 ;--拿來做最小值比對
    RT_NAME      VARCHAR2(20 BYTE);
    PER_CAP INTEGER ;--容納人數
    TTL_PEO INTEGER ;--包含加床的人數
BEGIN
    first_date_str  :=F_DATE;
    last_date_str   :=L_DATE;
    RT_NO           :=RT;
    DATE_COUNT      := first_date_str;--應該是旅客訂的開始日期(吧
    open c_odr;
        LOOP
            FETCH c_odr INTO my_odr.order_no , my_odr.start_date , my_odr.end_date , my_odr.quantity; 
                  EXIT WHEN c_odr%NOTFOUND; 
                  i := i + 1;
            odrs(i) := my_odr;
        END LOOP;
    close  c_odr;
    SELECT  quantity INTO max_QTY FROM room_type WHERE room_type_no = RT_NO;--賦值給max_QTY
    MIN_QTY := max_QTY;--用來記錄房間最後剩餘量，從max開始扣
----------上面這裡很可能錯  
    LOOP--把房型數量塞進TABLE
        DATE_TABLE(DATE_COUNT) := max_QTY;--把房型數量塞進去陣列
        DATE_COUNT := to_char(to_date(DATE_COUNT) + 1);
        IF ( to_date(DATE_COUNT) = last_date_str ) THEN
            EXIT;
        END IF;
    END LOOP;    
    index1 := DATE_TABLE.first;--INDEX1是VARCHAR2
    WHILE index1 IS NOT NULL LOOP--DATE_TABLE
        THIS_DATE := TO_DATE(index1);
        FOR i IN 1..odrs.COUNT LOOP
            IF ((odrs(i).start_date <= THIS_DATE) AND (THIS_DATE < odrs(i).end_date)) THEN
                DATE_TABLE(index1) := DATE_TABLE(index1) - odrs(i).quantity;
            END IF;
        END LOOP;
        IF(DATE_TABLE(index1) < MIN_QTY) THEN
            MIN_QTY := DATE_TABLE(index1);
        END IF;
        index1 := DATE_TABLE.next(index1);
    END LOOP;
    SELECT PERSON_CAPACITY, ROOM_TYPE_NAME, TOTAL_PEOPLE INTO PER_CAP, RT_NAME , TTL_PEO FROM ROOM_TYPE WHERE ROOM_TYPE_NO = RT;--容納人數
    
--    RETURN_TAB.LAST := ROOM_RE_O(RT, MIN_QTY, (SELECT PERSON_CAPACITY FROM ROOM_TYPE WHERE ROOM_TYPE_NO = RT), (SELECT TOTAL_PEOPLE FROM ROOM_TYPE WHERE ROOM_TYPE_NO = RT));
    IF (MIN_QTY > 0) THEN
        RETURN_TAB.extend;
    	RETURN_TAB(RETURN_TAB.LAST) := ROOM_RE_O(RT, RT_NAME,MIN_QTY, PER_CAP, TTL_PEO);
    END IF;
--    INSERT INTO ROOM_REMAIN (ROOM_TYPE_NO, REMAIN, PERSON_CAPACITY , TOTAL_PEOPLE )
--        VALUES(RT,MIN_QTY, (SELECT PERSON_CAPACITY FROM ROOM_TYPE WHERE ROOM_TYPE_NO = RT), (SELECT TOTAL_PEOPLE FROM ROOM_TYPE WHERE ROOM_TYPE_NO = RT));
END ROOM_REMAIN_SINGLE;
--------------------------------------------------------------------------------------------------------------

/
--test
--運算所有房型
create or replace PROCEDURE ROOM_REMAIN_ALL(F_DATE IN VARCHAR2, L_DATE IN VARCHAR2,RETURN_TAB out ROOM_RE_TAB)
AS
    
    CURSOR C_ROOM_TYPE IS SELECT ROOM_TYPE_NO FROM ROOM_TYPE;
BEGIN
RETURN_tab := ROOM_RE_TAB();--初始化table

--    DBMS_output.put_line(F_DATE||' 654 '||l_date);

--    DELETE FROM ROOM_REMAIN;
--    DBMS_SESSION.SET_NLS('nls_date_format', '"DD-MM-YYYY HH24:MI:SS"');
    begin
       execute immediate 'alter session set nls_date_format = ''yyyy-mm-dd''';
    end;
    FOR C IN C_ROOM_TYPE LOOP
        ROOM_REMAIN_SINGLE(F_DATE , L_DATE, C.ROOM_TYPE_NO, RETURN_TAB);
        EXIT WHEN C_ROOM_TYPE%NOTFOUND;
    END LOOP;
--    BEGIN
--        OPEN REMAIN FOR SELECT * FROM ROOM_REMAIN;
--    END;
END ROOM_REMAIN_ALL;

/

commit;