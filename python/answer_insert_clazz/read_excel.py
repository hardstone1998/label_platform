import pandas as pd


def read_clazz_and_answer():
    df = pd.read_excel(io="./故宫标注.xlsx", sheet_name="分类后的所有数据（包含知识库）")
    clazzName = {}
    for index, row in df.iterrows():
        i = row['分类'].find('.')
        if i == -1:
            continue
        clazz = row['分类'][i+1:]
        clazzName[row['回答']] = clazz
    return clazzName
