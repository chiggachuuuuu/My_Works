# Imports
import requests
import matplotlib.pyplot as plt
import datetime
import time

print(time.asctime())

def refresh_60():
    top_twenty = requests.get('https://api.polygon.io/v2/snapshot/locale/us/markets/stocks/losers?include_otc=false&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    tickers = [x['ticker'] for x in top_twenty['tickers']][:20]
    def mbym_price_plot(ticker, p):
        today = datetime.date.today()
        data = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{ticker}/range/1/minute/{today}/{today}?adjusted=true&sort=asc&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
        results = data['results']
        prices = [result['o'] if ('o' in result.keys()) else result['c'] if ('c' in result.keys()) else '' for result in results]
        prices = list(filter(lambda x: x != '', prices))
        plt.subplot(4,5,p)
        plt.title(ticker, fontsize=12)
        plt.plot(prices)
        plt.xticks([])
        plt.yticks(fontsize=7)
    p = 1
    for ticker in tickers:
        mbym_price_plot(ticker, p)
        p+=1
    now = datetime.datetime.now()
    plt.suptitle(now, fontsize=10)
    plt.subplots_adjust(hspace=0.3, wspace=0.3)
    figure = plt.gcf() 
    figure.set_size_inches(16, 8) 
    #plt.savefig('filename.png', bbox_inches='tight')
    manager = plt.get_current_fig_manager()
    manager.full_screen_toggle()
    plt.show(block=False)
    plt.pause(60)
    plt.close()

while True:
    refresh_60()

import signal
import sys

# Signal handler for SIGINT (Ctrl + C)
def signal_handler(sig, frame):
    print('\nStopping the program...')
    sys.exit(0)

# Register the signal handler
signal.signal(signal.SIGINT, signal_handler)
