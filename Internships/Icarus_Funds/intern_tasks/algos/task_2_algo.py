# Imports
import requests
import matplotlib.pyplot as plt
import datetime
import time

print(time.asctime())

# Create Variables
ticker = 'A'
today = datetime.date.today()

# Requests
data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/minute/{today}/{today}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
results = data['results']

# Minute Prices Lists
prices = [result['o'] if ('o' in result.keys()) else result['c'] if ('c' in result.keys()) else '' for result in results]
prices = list(filter(lambda x: x != '', prices))

# Plot
plt.title(ticker)
plt.plot(prices)
plt.show()

print(time.asctime())
