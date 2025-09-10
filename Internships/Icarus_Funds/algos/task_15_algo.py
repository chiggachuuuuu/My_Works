import requests

def tewentyor(ticker):
    data = requests.get(f'https://api.polygon.io/vX/reference/financials?ticker={ticker}&timeframe=quarterly&limit=20&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    if 'results' not in data.keys():
        print(f'No results for {ticker}')
    else:
        results = data['results']
        if len(results) == 20:
            return (True, results)
        else:
            return (False, [])
def get_incomestatement_five(ticker):
    fullrank, results = tewentyor(ticker)
    if fullrank:
        fst = results[:4]
        sec = results[4:8]
        thrd = results[8:12]
        fourth = results[12:16]
        fifth = results[16:]
        results = [fst, sec, thrd, fourth, fifth]
        revenues = []
        operating_incomes = []
        for result in results:
            revenue = 0
            operating_income = 0
            for item in result:
                incomestatement = item['financials']['income_statement']
                revenue += incomestatement['revenues']['value']
                operating_income += incomestatement['operating_income_loss']['value']
            revenues.append(revenue)
            operating_incomes.append(operating_income)
        return (revenues, operating_incomes)
    else:
        pass

with open ("listofthousandtickers.txt", "r") as file:
    tickers = file.read().splitlines()

incomegrowth = []
for ticker in tickers:
    try:
        revenues, operating_incomes = get_incomestatement_five(ticker)
        if (revenues[0] > revenues[1] > revenues[2] > revenues[3] > revenues[4]) and (operating_incomes[0] > operating_incomes[1] > operating_incomes[2] > operating_incomes[3] > operating_incomes[4]):
            incomegrowth.append(ticker)
        else:
            pass
    except:
        pass
print(len(incomegrowth))
print(incomegrowth)
