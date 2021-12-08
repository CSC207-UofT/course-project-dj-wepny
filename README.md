# CSC207 Project - Personal Health App

CSC207 Project by DJ WEPNY. This project encodes for a Personal Health system that can create a meal plan, output BMI and EER, suggest workout ideas,
and calculate risk factors for certain diseases through based on information provided by the user.

## Table of contents
* [General guide](#general-guide)
* [BMI](#bmi)
* [EER](#eer)
* [Exercise](#exercise)
* [Disease](#disease)
* [Meal Plan](#meal-plan)

## General guide
When the program is run, the user will be prompted to either log in or create a new account. New users will enter basic information such as their name, gender, age, height, and weight. 
Then, the user will be asked to choose 1 of 5 functionalities: analyze Body Mass Index (BMI), 
analyze Energy Required Per Day (EER), provide workout suggestions, analyze disease risk, and generate a meal plan.

## BMI
The system will provides a description of what BMI is and why it's important. The user's BMI will then be calculated based on
their height and weight, and classifies the user as either underweight, healthy, overweight, or obese.

## EER
Similar to the BMI function, the EER, which is the User's Energy Requirement per day (measured in kcal/day) will be calculated based on the user's height, weight, age and their exercise levels. This will be calculated using the equation 
found [here](https://www.ncbi.nlm.nih.gov/pmc/articles/PMC1784117/). The system will output the user's energy requirement per day and explains what this number represents and possibly indicate some beneficial advice for their health.

## Exercise
The user will be asked to input what specific body part they'd like to target and
what equipment they own. The app will then suggest possible exercises the user could perform, along with information
about the major and minor muscles those exercises will work, and what equipment they may need.

## Disease
The app will ask users whether they are experiencing some common symptoms. The user will then receive information
about possible diseases they could be at risk for.

## Meal Plan
Users will answer some simple questions such as whether they prefer food with low carbs, low sugar, low fat, and/or whether they are vegetarian.
The user will also be asked to specify how many food item recommendations they would like, and the program will use this information to generate a list of foods based on their desired requirements.
