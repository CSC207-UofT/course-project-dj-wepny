# CSC207 Project - Personal Health App

CSC207 Project by DJ WEPNY. This project encodes for a Personal Health app that can create a meal plan, output BMI and EER, suggest workout ideas,
and calculate risk factors for certain diseases through based on information provided by the user.

## Table of contents
* [General guide](#general-guide)
* [BMI](#bmi)
* [EER](#eer)
* [Exercise](#exercise)
* [Disease](#disease)
* [Meal Plan](#meal-plan)

## General guide
When the program is run, the user will be prompted to enter basic information such as their name, gender, and age. 
Then, the user will be asked to choose 1 of 5 functionalities: analyze Body Mass Index (BMI), 
analyze Energy Required Per Day (EER), provide workout suggestions, analyze disease risk, and generate a meal plan.

## BMI
The app provides a description of what BMI is and why it's important. The user's BMI will then be provided based on
their height and weight, and classifies the user as either underweight, healthy, overweight, or obese.

## EER
Similar to the BMI function, the EER of the user will be calculated based on their height, weight, and age. The user 
will also be asked to input their exercise levels. This will be calculated using the equation 
found [here](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1784117/).

## Exercise
The user will be asked to input what specific body part they'd like to target, what type of exercise they like, and 
what equipment they own. The app will then suggest possible exercises the user could perform, along with information
about the major and minor muscles those exercises will work, and what equipment they may need.

## Disease
The app will ask users whether they are experiencing some common symptoms. The user will then receive information
about possible diseases they could be at risk for, a short summary of its implications, and its severity.

## Meal Plan
Users will enter their food preferences, such as food they like and dislike and whether they are vegetarian or not.
The user may also specify their health goals, such as weight loss, gain, or maintenance. The app will then use this 
information as well as the user's EER to generate a meal plan within their desired calorie range containing foods 
hey may like.
