# project.py


import pandas as pd
import numpy as np
from pathlib import Path
import re
import requests
import time


# ---------------------------------------------------------------------
# QUESTION 1
# ---------------------------------------------------------------------


def get_book(url):
    time.sleep(1/2)
    response = requests.get(url).text
    processed_corpus = response.replace('\r\n', '\n').split('***')[2]
    return processed_corpus


# ---------------------------------------------------------------------
# QUESTION 2
# ---------------------------------------------------------------------


def tokenize(book_string):
    stripped = book_string.strip()
    tokens = re.sub(r'\n{2,}', '\x03\x02', stripped)
    tokens = re.findall(r"\w+|[^ _\n]", tokens)
    tokens = ['\x02'] + tokens + ['\x03']
    return tokens


# ---------------------------------------------------------------------
# QUESTION 3
# ---------------------------------------------------------------------


class UniformLM(object):


    def __init__(self, tokens):

        self.mdl = self.train(tokens)
        
    def train(self, tokens):
        unique_tokens = list(set(tokens))
        probs_list = [1/len(unique_tokens) for i in range(len(unique_tokens))]
        train_dict = dict(map(lambda token, prob : (token, prob) , unique_tokens, probs_list))
        return pd.Series(train_dict)
    
    def probability(self, words):
        count = np.sum([x in self.mdl.index for x in words])
        if (count == len(words)):
            return self.mdl[0]**count
        else:
            return 0
        
    def sample(self, M):
        tokens = np.random.choice(self.mdl.index, size=M, p=self.mdl)
        return ' '.join(tokens)


# ---------------------------------------------------------------------
# QUESTION 4
# ---------------------------------------------------------------------


class UnigramLM(object):
    
    def __init__(self, tokens):

        self.mdl = self.train(tokens)
    
    def train(self, tokens):
        total_count = len(tokens)
        probs = pd.Series(tokens).value_counts()
        return probs/total_count
    
    def probability(self, words):
        prob = 1
        if (len(words) == 0):
            return 0
        for i in range(len(words)):
            if (words[i] not in self.mdl.index):
                return 0
            prob *= self.mdl[words[i]]      
        return prob
        
    def sample(self, M):
        tokens = np.random.choice(self.mdl.index, size=M, p=self.mdl)
        return ' '.join(tokens)


# ---------------------------------------------------------------------
# QUESTION 5
# ---------------------------------------------------------------------


class NGramLM(object):
    
    def __init__(self, N, tokens):
        # You don't need to edit the constructor,
        # but you should understand how it works!
        
        self.N = N

        ngrams = self.create_ngrams(tokens)

        self.ngrams = ngrams
        self.mdl = self.train(ngrams)

        if N < 2:
            raise Exception('N must be greater than 1')
        elif N == 2:
            self.prev_mdl = UnigramLM(tokens)
        else:
            self.prev_mdl = NGramLM(N-1, tokens)

    def create_ngrams(self, tokens):
        r_list = list()
        for i in range(len(tokens) - self.N + 1):
            r_list.append(tuple([tokens[i+x] for x in range(self.N)]))
        return r_list
    
    def train(self, ngrams):
        ngram_count = pd.Series(ngrams).value_counts()
        n1gram = pd.Series(ngrams).transform(lambda x: tuple(x[:-1]))
        n1gram_count = n1gram.value_counts()
        unique_n = pd.Series(ngram_count.index)
        unique_n1 = unique_n.transform(lambda x: tuple(x[:-1]))
        df = pd.DataFrame({'ngram': unique_n, 'n1gram': unique_n1})
        df = df.assign(prob = unique_n.transform(lambda x: ngram_count[x]) / unique_n1.transform(lambda x: n1gram_count[x]))
        return df
    def probability(self, words):
        prob = 1
        def low(model, words, prob):
            given_prob = prob
            for i in range(len(words)):
                curr_mdl = model
                for j in range((model.N - i - 1)):
                    curr_mdl = curr_mdl.prev_mdl
                mdl = curr_mdl.mdl
                if isinstance(mdl, pd.Series):
                    if (words[i] in list(mdl.index)):
                        given_prob *= mdl[words[i]]
                    else:
                        given_prob *= 0
                else:
                    word_tup = tuple(words[:i+1])
                    if (word_tup in list(mdl['ngram'])):
                        wiz_index = mdl.set_index('ngram')
                        given_prob *= wiz_index['prob'][word_tup]
                    else:
                        given_prob *= 0
            return given_prob
        
        def high(model, words, prob):
            given_prob = prob
            mdl = model.mdl
            for i in range(model.N - 1, len(words)):
                word_tup = tuple(words[i-model.N+1:i+1])
                if (word_tup in list(mdl['ngram'])):
                    wiz_index = mdl.set_index('ngram')
                    given_prob *= wiz_index['prob'][word_tup]
                else:
                    given_prob *= 0
            return given_prob
        
        if (len(words) < self.N):
            probs = low(self, words, prob)
        else:
            low_prob = low(self, words[:self.N - 1], prob)
            probs = high(self, words, low_prob)
            
        return probs
    
    def sample(self, M):
        def helper(N):
            if N == 1:
                df = self.mdl[self.mdl['ngram'].apply(lambda x: x[0] == '\x02')]
                if len(df) == 0:
                    return ['\x02']
                samples  = list(df.sample(n=1)['ngram'])[0][:2]
                return samples
            elif N < self.N:
                samples = helper(N - 1)
                def filter_rows(row_tuple):
                    return row_tuple[:N] == tuple(samples)
                df = self.mdl[self.mdl['ngram'].apply(filter_rows)]
                if len(df) == 0:
                    return samples + ['\x03']
                sample_2  = list(df.sample(n=1)['ngram'])[0][N:N+1]
                return samples + sample_2
            else:
                samples = helper(N - 1)
                def filter_rows_2(row_tuple):
                    return row_tuple[:self.N - 1] == tuple(samples[len(samples) - self.N + 1:])
                df = self.mdl[self.mdl['ngram'].apply(filter_rows_2)]
                # print(df)
                if len(df) == 0:
                    return samples + ['\x03']
                sample_2  = list(df.sample(n=1)['ngram'])[0][self.N - 1:self.N]
                return samples + sample_2
        
        output = helper(M)
        output = list(output)
        if output[-1] != '\x03':
            output[-1] = '\x03'
        return ' '.join(output)
