--清空旅客會員ID跟資料 / 更改房間狀態
--判斷訂單更新的狀態試"完成"
create or replace TRIGGER trigger_alter_checkout_room
  AFTER update 
    ON BOOKING_ORDER_MASTER
  FOR EACH ROW
BEGIN
    IF(:new.status = 2) THEN--若新增的狀態為訂單已完成(已退房)
            UPDATE ROOM  SET ROOM_STATUS = 0,
                            CUS_ID      = NULL,
                            TENANT_NAME = NULL,
                            TENANT_PHONE = NULL
                WHERE CUS_ID = :new.CUS_ID;       
    END IF;
END;
/
drop TRIGGER trigger_alter_checkout_room;
/