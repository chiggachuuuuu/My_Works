import requests

data = requests.get('https://api.polygon.io/v2/snapshot/locale/us/markets/stocks/tickers?include_otc=false&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()

tickers = [x['ticker'] for x in data['tickers']]
print(len(tickers))

with open("task5.txt", "w") as f:
    for ticker in tickers:
        f.write(ticker)
        f.write('\n')
