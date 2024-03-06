import mysql.connector


def select_answer_all():
    global cursor
    cnx = mysql.connector.connect(
        host="36.112.150.185",
        user="root",
        password="ctcc@2017",
        database="asr-identify",
        port="3305"
    )
    try:
        cursor = cnx.cursor()
        select_query = "select id,content from answer "
        cursor.execute(select_query)
        result = cursor.fetchall()
        return result
    except Exception as e:
        print("查询数据库异常")
        raise Exception(e)
    finally:
        cursor.close()
        cnx.close()

def select_clazz_id():
    global cursor
    cnx = mysql.connector.connect(
        host="36.112.150.185",
        user="root",
        password="ctcc@2017",
        database="asr-identify",
        port="3305"
    )
    try:
        cursor = cnx.cursor()
        select_query = "select id,label from class_1"
        cursor.execute(select_query)
        result = cursor.fetchall()
        clazzId = {}
        for r in result:
            clazzId[r[1]] = r[0]
        return clazzId
    except Exception as e:
        print("查询数据库异常")
        raise Exception(e)
    finally:
        cursor.close()
        cnx.close()

def update_clazzId(id,clazzId):
    global cursor
    cnx = mysql.connector.connect(
        host="36.112.150.185",
        user="root",
        password="ctcc@2017",
        database="asr-identify",
        port="3305"
    )
    try:
        cursor = cnx.cursor()
        update_query = "update answer set clazz_id = %s where id= %s "
        data = [clazzId,id]
        cursor.execute(update_query, data)
        cnx.commit()
        print("插入数据库成功")
    except Exception as e:
        print("插入数据库异常")
        print(e)
    finally:
        cursor.close()
        cnx.close()
