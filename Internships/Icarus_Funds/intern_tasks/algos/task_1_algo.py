# Imports
import time
import requests

# Start Time
print(time.asctime())

# Open prices printing function
def open_prices(tickers):
    for ticker in tickers:
        data = requests.get(f'https://api.polygon.io/v1/open-close/{ticker}/2024-06-17?adjusted=true&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
        if ('open' not in data.keys()):
            pass
        else:
            print(ticker, data['open'])

# Implementation
file = open('listofthousandtickers.txt', 'r')
tickers = file.read().splitlines()
file.close()
open_prices(tickers)

# End Time
print(time.asctime())
