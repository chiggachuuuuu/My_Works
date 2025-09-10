import numpy as np
import pandas as pd

def get_features(words):
    features = []
    letters = set("abcdefghijklmnopqrstuvwxyz")
    suffixes = []
    for word in words:
        suffixes.append(word[-3:])
    for word in words:
        word = word.lower()
        uni_counts = {}
        bi_counts = {}
        suffix_counts = {}
        for letter in word:
            if letter in letters:
                if letter in uni_counts:
                    uni_counts[letter] += 1
                else:
                    uni_counts[letter] = 1
        for i in range(len(word) - 1):
            bi = word[i:i+2]
            if bi in bi_counts:
                bi_counts[bi] += 1
            else:
                bi_counts[bi] = 1
        for suffix in suffixes:
            if word.endswith(suffix):
                suffix_counts[suffix] = 1
            else:
                suffix_counts[suffix] = 0  
        feature_vector = {**uni_counts, **bi_counts, **suffix_counts}
        features.append(feature_vector)
    
    return features, suffixes

def train(train_words, train_labels):
    train_features, train_suffixes = get_features(train_words)
    prior_probs = {"spanish": train_labels.count("spanish") / len(train_labels), "french": train_labels.count("french") / len(train_labels)}
    feature_counts = {"spanish": {}, "french": {}}
    total_counts = {"spanish": 0, "french": 0}
    for i, features in enumerate(train_features):
        label = train_labels[i]
        for feature, count in features.items():
            if feature not in feature_counts[label]:
                feature_counts[label][feature] = 0
            feature_counts[label][feature] += count
            total_counts[label] += count
    feature_probs = {"spanish": {feature: feature_counts["spanish"][feature] / total_counts["spanish"] for feature in feature_counts["spanish"]},
        "french": {feature: feature_counts["french"][feature] / total_counts["french"] for feature in feature_counts["french"]}}
    
    return prior_probs, feature_probs

def predict(test_words, prior_probs, feature_probs):
    test_features, test_suffixes = get_features(test_words)
    predictions = []
    
    for features in test_features:
        spanish_prob = prior_probs["spanish"]
        french_prob = prior_probs["french"]
        for feature, count in features.items():
            if feature in feature_probs["spanish"]:
                spanish_prob *= feature_probs["spanish"][feature] ** count
            if feature in feature_probs["french"]:
                french_prob *= feature_probs["french"][feature] ** count
        for suffix in test_suffixes:
            if suffix in features and features[suffix] == 1:
                if suffix in feature_probs["spanish"]:
                    spanish_prob *= feature_probs["spanish"][suffix]
                else:
                    spanish_prob *= 1
                if suffix in feature_probs["french"]:
                    french_prob *= feature_probs["french"][suffix]
                else:
                    french_prob *= 1
        if spanish_prob > french_prob:
            predictions.append("spanish")
        else:
            predictions.append("french")  
    return predictions

def classify(train_words, train_labels, test_words):
    train_labels = list(train_labels) 
    prior_probs, feature_probs= train(train_words, train_labels)
    return predict(test_words, prior_probs, feature_probs)
