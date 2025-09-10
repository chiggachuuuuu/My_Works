# Imports
import requests
import datetime

def get_news(ticker):
    today = datetime.date.today()
    news = requests.get(f'https://api.polygon.io/v2/reference/news?ticker={ticker}&published_utc={today}&limit=10&apiKey=1SEwFpxQSiQqHp2soaX0QSYtGpVitHoz').json()
    if (len(news['results'])==0):
        print(f'News not found for {ticker} at {today}')
    else:
        for result in news['results']:
            print(result['article_url'])

ticker = input('Enter Ticker Name to retrieve ticker news:')
get_news(ticker)
