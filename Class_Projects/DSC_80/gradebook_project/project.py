# project.py


import pandas as pd
import numpy as np
from pathlib import Path

import plotly.express as px


# ---------------------------------------------------------------------
# QUESTION 1
# ---------------------------------------------------------------------


def get_assignment_names(grades):
    c_names = grades.columns.tolist()
    r_dict = {}
    r_dict['lab'] = list(filter(
        lambda x: ('lab' in x) and ('-' not in x), grades.columns))
    r_dict['project'] = list(filter(
        lambda x: ('project' in x) and ('-' not in x) and ('_' not in x),
        grades.columns))
    r_dict['midterm'] = list(filter(
        lambda x: ('Midterm' in x) and ('-' not in x), grades.columns))
    r_dict['final'] = list(filter(
        lambda x: ('Final' in x) and ('-' not in x), grades.columns))
    r_dict['disc'] = list(filter(
        lambda x: ('disc' in x) and ('-' not in x), grades.columns))
    r_dict['checkpoint'] = list(filter(
        lambda x: ('checkpoint' in x)and ('-' not in x), grades.columns))
    return r_dict


# ---------------------------------------------------------------------
# QUESTION 2
# ---------------------------------------------------------------------


def projects_total(grades):
    num_projects = len(get_assignment_names(grades)['project'])
    sum_projects = pd.Series(0, index = np.arange(grades.shape[0]))
    for i in range(num_projects):
        n = f'project{i+1:02}'
        all_n = [x for x in list(grades) if n in x]
        max_score = grades[[x for x in all_n if ('Max' in x) and
                            ('check' not in x)]].fillna(0).sum(axis=1)
        total_score = grades[[x for x in all_n if ('Late' not in x) and
                              ('Max' not in x) and
                              ('check' not in x)]].fillna(0).sum(axis=1)
        score = total_score/max_score
        sum_projects += score
    return sum_projects / num_projects


# ---------------------------------------------------------------------
# QUESTION 3
# ---------------------------------------------------------------------


def lateness_penalty(col):
    w_hrs = 7 * 24
    one_w = 1 * w_hrs
    two_w = 2* w_hrs
    grace_p = 2
    splited = col.str.split(':')
    penalty_mult = pd.Series(1.0, index = np.arange(col.size))
    for i in range(col.size):
        time = [int(x) for x in splited.iloc[i]]
        if (time[1] == 0) and (time[2] == 0):
            if (one_w >= time[0] > grace_p):
                penalty_mult.iloc[i] *= 0.9
            elif (two_w >= time[0] > one_w):
                penalty_mult.iloc[i] *= 0.7
            elif (time[0] > two_w):
                penalty_mult.iloc[i] *= 0.4
        else:
            if (one_w > time[0] >= grace_p):
                penalty_mult.iloc[i] *= 0.9
            elif (two_w > time[0] >= one_w):
                penalty_mult.iloc[i] *= 0.7
            elif (time[0] >= two_w):
                penalty_mult.iloc[i] *= 0.4
    return penalty_mult

# ---------------------------------------------------------------------
# QUESTION 4
# ---------------------------------------------------------------------


def process_labs(grades):
    labs = get_assignment_names(grades)['lab']
    r_df = pd.DataFrame(columns = labs)
    for i in range(len(labs)):
        score = grades[f'lab{i+1:02}'].fillna(0)
        max_score = grades[f'lab{i+1:02} - Max Points'].fillna(0)
        mult = lateness_penalty(grades[f'lab{i+1:02} - Lateness (H:M:S)'])
        r_df[f'lab{i+1:02}'] = score / max_score * mult
    return r_df


# ---------------------------------------------------------------------
# QUESTION 5
# ---------------------------------------------------------------------


def lab_total(processed):
    n = processed.shape[1] - 1
    final_sum = processed.sum(axis=1) - processed.min(axis=1)
    return final_sum/n


# ---------------------------------------------------------------------
# QUESTION 6
# ---------------------------------------------------------------------


def total_points(grades):
    cols = get_assignment_names(grades)
    lab_point = lab_total(process_labs(grades)) * 0.2
    project_point = projects_total(grades) * 0.3
    dis = cols['disc']
    sum_di = pd.Series(0, index = np.arange(grades.shape[0]))
    for i in range(len(dis)):
        score = grades[f'discussion{i+1:02}'].fillna(0)
        max_score = grades[f'discussion{i+1:02} - Max Points'].fillna(0)
        sum_di += score/max_score
    di_point =  sum_di/len(dis) * 0.025
    cps = cols['checkpoint']
    sum_cps = pd.Series(0, index = np.arange(grades.shape[0]))
    for cp in cps:
        score = grades[cp].fillna(0)
        max_score = grades[cp + ' - Max Points'].fillna(0)
        sum_cps += score/max_score
    cp_point = sum_cps/len(cps) * 0.025
    midterm_point = grades['Midterm']/grades['Midterm - Max Points'] * 0.15
    final_point = grades['Final']/grades['Final - Max Points'] * 0.3
    return lab_point + project_point + di_point + cp_point + midterm_point.fillna(0) + final_point.fillna(0)


# ---------------------------------------------------------------------
# QUESTION 7
# ---------------------------------------------------------------------


def final_grades(total):
    letter_grade = []
    for point in total:
        if (point >= 0.9 ):
            letter_grade.append('A')
        elif (0.9 > point >= 0.8):
            letter_grade.append('B')
        elif (0.8 > point >= 0.7):
            letter_grade.append('C')
        elif (0.7 > point >= 0.6):
            letter_grade.append('D')
        else:
            letter_grade.append('F')
    return pd.Series(letter_grade)

def letter_proportions(total):
    letter = final_grades(total)
    proportions = letter.value_counts()/len(letter)
    return proportions.sort_values(ascending = False)


# ---------------------------------------------------------------------
# QUESTION 8
# ---------------------------------------------------------------------


def raw_redemption(final_breakdown, question_numbers):
    redemp_questions = final_breakdown.fillna(0)
    redemp_questions = redemp_questions.iloc[:,[0] + question_numbers] 
    total_redemption_percentage = redemp_questions.iloc[:, 1:].sum(axis=1) / redemp_questions.iloc[:, 1:].sum(axis=1).max()
    output = pd.DataFrame({'PID': final_breakdown['PID'] , 'Raw Redemption Score': total_redemption_percentage})
    return output
    
def combine_grades(grades, raw_redemption_scores):
    combined = grades.merge(raw_redemption_scores, left_on='PID', right_on='PID')
    return combined


# ---------------------------------------------------------------------
# QUESTION 9
# ---------------------------------------------------------------------


def z_score(ser):
    std = np.std(ser, ddof=0)
    mean = np.mean(ser)
    series = (ser - mean) / std
    return series
    
def add_post_redemption(grades_combined):
    pre_midterm = grades_combined['Midterm'].fillna(0)/grades_combined['Midterm - Max Points'].max()
    grades_combined['Midterm Score Pre-Redemption'] = pre_midterm
    pre_z = z_score(pre_midterm)
    post_z = z_score(grades_combined['Raw Redemption Score'])
    selecting = post_z > pre_z
    pre_z[selecting] = post_z[selecting]
    post_midterm = pre_z * np.std(pre_midterm, ddof=0) + np.mean(pre_midterm)
    grades_combined['Midterm Score Post-Redemption'] = post_midterm
    return grades_combined


# ---------------------------------------------------------------------
# QUESTION 10
# ---------------------------------------------------------------------


def total_points_post_redemption(grades_combined):
    cols = get_assignment_names(grades_combined)
    lab_point = lab_total(process_labs(grades_combined)) * 0.2
    project_point = projects_total(grades_combined) * 0.3
    dis = cols['disc']
    sum_di = pd.Series(0, index = np.arange(grades_combined.shape[0]))
    for i in range(len(dis)):
        score = grades_combined[f'discussion{i+1:02}'].fillna(0)
        max_score = grades_combined[f'discussion{i+1:02} - Max Points'].fillna(0)
        sum_di += score/max_score
    di_point =  sum_di/len(dis) * 0.025
    cps = cols['checkpoint']
    sum_cps = pd.Series(0, index = np.arange(grades_combined.shape[0]))
    for cp in cps:
        score = grades_combined[cp].fillna(0)
        max_score = grades_combined[cp + ' - Max Points'].fillna(0)
        sum_cps += score/max_score
    cp_point = sum_cps/len(cps) * 0.025
    midterm_point = grades_combined['Midterm Score Post-Redemption'] * 0.15
    final_point = grades_combined['Final']/grades_combined['Final - Max Points'] * 0.3
    return lab_point + project_point + di_point + cp_point + midterm_point.fillna(0) + final_point.fillna(0)
        
def proportion_improved(grades_combined):
    old_grade = final_grades(total_points(grades_combined))
    new_grade = final_grades(total_points_post_redemption(grades_combined))
    return np.mean(old_grade != new_grade)


# ---------------------------------------------------------------------
# QUESTION 11
# ---------------------------------------------------------------------


def section_most_improved(grades_analysis):
    comparison = grades_analysis[['Section', 'Letter Grade Pre-Redemption', 'Letter Grade Post-Redemption']]
    comparison['Improved'] = comparison['Letter Grade Post-Redemption'] != comparison['Letter Grade Pre-Redemption']
    most_improved_sec = comparison.groupby('Section').mean().get('Improved').idxmax()
    return most_improved_sec
    
def top_sections(grades_analysis, t, n):
    filtered = grades_analysis[(grades_analysis['Final'] / grades_analysis['Final - Max Points']) >= t]
    sections = filtered.groupby('Section').size() 
    top_sec = sections[sections > n].index.values
    return top_sec


# ---------------------------------------------------------------------
# QUESTION 12
# ---------------------------------------------------------------------


def rank_by_section(grades_analysis):
    by_sec = grades_analysis.groupby('Section')
    max_rank = by_sec['PID'].count().max()
    sections = by_sec.count().index
    rank_by_sec = pd.DataFrame(columns=sections)
    rank_by_sec['Section Rank'] = np.arange(max_rank + 1)[1:]
    sorted_grades = grades_analysis.sort_values(by='Total Points Post-Redemption', ascending = False)
    for sec in sections:
        rank_by_sec[sec] = pd.Series(sorted_grades[sorted_grades['Section'] == sec]['PID'].values)
    rank_by_sec = rank_by_sec.set_index('Section Rank')
    return rank_by_sec.fillna('')


# ---------------------------------------------------------------------
# QUESTION 13
# ---------------------------------------------------------------------


def letter_grade_heat_map(grades_analysis):
    letter_grade_distribution = grades_analysis.pivot_table(
    values='PID',
    index='Letter Grade Post-Redemption', 
    columns='Section', 
    aggfunc='count',
    fill_value=0
    )
    proportion_distribution = letter_grade_distribution/letter_grade_distribution.sum()
    fig = px.imshow(proportion_distribution, template='simple_white', title='Distribution of Letter Grades by Section')
    return fig
