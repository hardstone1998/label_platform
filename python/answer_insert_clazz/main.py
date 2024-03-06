import answer_insert_clazz.read_excel
import answer_insert_clazz.mysql_con_answer

if __name__ == '__main__':
    clazzName = answer_insert_clazz.read_excel.read_clazz_and_answer()
    clazzIdMap = answer_insert_clazz.mysql_con_answer.select_clazz_id()
    answerList = answer_insert_clazz.mysql_con_answer.select_answer_all()
    for answer in answerList:
        if answer[1] in clazzName:
            clazzId = clazzIdMap[clazzName[answer[1]]]
            print(clazzId)
            answer_insert_clazz.mysql_con_answer.update_clazzId(answer[0],clazzId)
