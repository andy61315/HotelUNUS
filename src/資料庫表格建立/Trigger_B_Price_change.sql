---------------------------------------------------------


--BEGIN
--ROOM_REMAIN_ALL_try('2020-05-01', '2020-05-08');
--END;

--加入房間時，自動生成把房型的數量+1
CREATE OR REPLACE TRIGGER trigger_room_insert
  AFTER insert 
    ON room
  FOR EACH ROW
DECLARE
    QTY NUMBER(3,0);
BEGIN
    SELECT RT.QUANTITY INTO QTY FROM ROOM_TYPE RT WHERE RT.ROOM_TYPE_NO = :new.ROOM_TYPE_NO;
    UPDATE ROOM_TYPE  SET QUANTITY = QTY + 1
         WHERE ROOM_TYPE_NO = :new.ROOM_TYPE_NO;
        ROOM_COMBINATION('');      
END;
/
--修改
CREATE OR REPLACE TRIGGER trigger_room_update
  AFTER UPDATE OF 
      ROOM_STATUS
    ON ROOM
  FOR EACH ROW
DECLARE
    QTY NUMBER(3,0);
    CHANGE_NUM NUMBER(1);
BEGIN
    IF ((:OLD.ROOM_STATUS = 5) AND (:NEW.ROOM_STATUS != 5)) THEN
        CHANGE_NUM := 1 ;
    ELSIF ((:OLD.ROOM_STATUS != 5) AND( :NEW.ROOM_STATUS = 5)) THEN
        CHANGE_NUM := -1 ;
    ELSE 
        CHANGE_NUM := 0 ;
    END IF;
    
    SELECT RT.QUANTITY INTO QTY FROM ROOM_TYPE RT WHERE RT.ROOM_TYPE_NO = :new.ROOM_TYPE_NO;
    UPDATE ROOM_TYPE  SET QUANTITY = (QTY + CHANGE_NUM)
         WHERE ROOM_TYPE_NO = :new.ROOM_TYPE_NO;
     ROOM_COMBINATION('');      
END;


/

--訂房訂單明細修改時，更改主檔總金額
CREATE OR REPLACE TRIGGER trigger_Total_Price_update
  AFTER UPDATE OF 
      PRICE
    ON BOOKING_ORDER_DETAIL
  FOR EACH ROW
DECLARE
    P NUMBER;
BEGIN
    SELECT TOTAL_PRICE INTO P FROM BOOKING_ORDER_MASTER WHERE B_ORDER_NO = :new.B_ORDER_NO;
  IF ( :new.PRICE != :old.PRICE ) THEN
    UPDATE BOOKING_ORDER_MASTER SET TOTAL_PRICE = P + (:new.PRICE - :old.PRICE) 
    WHERE B_ORDER_NO = :old.B_ORDER_NO;
  END IF;
END;
/
--訂房訂單明細新增時，更改主檔總金額
CREATE OR REPLACE TRIGGER trigger_Total_Price_insert
  AFTER insert 
    ON BOOKING_ORDER_DETAIL
  FOR EACH ROW
DECLARE
    P NUMBER;
BEGIN
    SELECT TOTAL_PRICE INTO P FROM BOOKING_ORDER_MASTER WHERE B_ORDER_NO = :new.B_ORDER_NO;
    UPDATE BOOKING_ORDER_MASTER SET TOTAL_PRICE = P + :new.PRICE 
         WHERE B_ORDER_NO = :new.B_ORDER_NO;
END;
/
--訂房訂單明細修改時，更改主檔總金額
CREATE OR REPLACE TRIGGER trigger_Total_Price_delete
  AFTER delete 
    ON BOOKING_ORDER_DETAIL
  FOR EACH ROW
DECLARE
    P NUMBER;
BEGIN
    SELECT TOTAL_PRICE INTO P FROM BOOKING_ORDER_MASTER WHERE B_ORDER_NO = :old.B_ORDER_NO;
    UPDATE BOOKING_ORDER_MASTER SET TOTAL_PRICE = P - :old.PRICE 
         WHERE B_ORDER_NO = :old.B_ORDER_NO;
END;
/

--刪除流水號的procedure
create or replace procedure test_p2
is
begin
--    訂房訂單
    execute immediate 'DROP SEQUENCE SEQ_B_ORDER_NO';
    execute immediate 'CREATE SEQUENCE SEQ_B_ORDER_NO';
    
--    客房訂餐訂單
    execute immediate 'DROP SEQUENCE SEQ_ROOM_MEAL_ORDER_NO';
    execute immediate 'CREATE SEQUENCE SEQ_ROOM_MEAL_ORDER_NO';
    
--  餐廳用餐訂單


    execute immediate 'DROP SEQUENCE SEQ_RES_MEAL_ORDER_NO';
    execute immediate 'CREATE SEQUENCE SEQ_RES_MEAL_ORDER_NO';
end test_p2;
/
BEGIN
  dbms_scheduler.drop_job(job_name => 'my_job_test');
END;


/
--執行排程器
BEGIN 
     dbms_scheduler.create_job(job_name        => 'my_job_test',--新排程器的名字
                             job_type        => 'STORED_PROCEDURE',--要執行的區塊的類型
                             job_action      => 'test_p2',--要執行的procedure的名字
                              start_date      => '17-APR-20 12.00.00 AM',--開始時間
                             repeat_interval => 'FREQ=DAILY',--執行間隔
                             enabled         => TRUE,
                             comments        => 'test');
end;
    
     /
     
--員工離職時刪除其權限
CREATE OR REPLACE TRIGGER trigger_when_emp_quit
  AFTER UPDATE OF 
      EMP_STATUS ON EMPLOYEE
  FOR EACH ROW
BEGIN
  IF ( :NEW.EMP_STATUS = 0) THEN--:NEW代表"該筆資料修改後的資料"，也可以取NEW:EMP_ID之類的值(當然就會跟舊的一樣
    DELETE FROM EMP_AUTHORITY WHERE EMP_ID = :NEW.EMP_ID;
  END IF;
END;

--如果要寫好幾個TRIGGER或PL/SQL的FUNCTION的話，每個FUNCTION之間要用 "/" 隔開
/
commit;