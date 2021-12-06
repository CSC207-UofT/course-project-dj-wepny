//package consoleforgui;
//
//import constants.EERConstants;
//import constants.Exceptions;
//import constants.ExerciseConstants;
//import constants.SystemConstants;
//import controllers.Presenter;
//import controllers.RunCommand;
//
//import java.util.*;
//
//public class HelperUserInfo {
//    /**
//     * Helper function that lets the user input their activity level
//     *
//     * @param reader reads the user input
//     * @return the activity level represented by the user's input
//     */
//    public static String activityLevel(Scanner reader) {
//
////        System.out.println(Constants.ACTIVITY_MENU);
//        Presenter.ActivityLevelPrompt("menu");
//        String userActivityLevel = reader.nextLine();
//
//        //check to make sure the input is a number within the correct range
//        while (HelperConsole.isNotNum(userActivityLevel) ||
//                HelperConsole.notInRange(Integer.parseInt(userActivityLevel), 3)) {
////            System.out.println(Constants.INVALID_INPUT);
//            Presenter.ActivityLevelPrompt("invalid");
//            userActivityLevel = reader.nextLine();
//        }
//
//        switch (userActivityLevel) {
//            case "1":
//                return EERConstants.SED;
//            case "2":
//                return EERConstants.LOW;
//            case "3":
//                return EERConstants.MID;
//            case "4":
//                return EERConstants.HIGH;
//            default:
//                return Exceptions.INVALID_INPUT;
//        }
//    }
//
//    /**
//     * Helper function that lets the user input their exercise preference
//     *
//     * @param reader reads in the user input
//     * @return a string of the user's input
//     */
//    public static String[] exercisePreference(Scanner reader) {
//
////        System.out.println(Constants.EXERCISE_START + Constants.EXERCISE_MAJOR);
//        Presenter.ExercisePrompt("start");
//        String majorMuscle = reader.nextLine();
//
//        // making sure the user input is a valid major muscle
//        while (!ExerciseConstants.ALL_MAJOR_MUSCLES.contains(majorMuscle)) {
////            System.out.println(Constants.EXERCISE_MAJOR_ERROR);
//            Presenter.ExercisePrompt("major error");
//            majorMuscle = reader.nextLine();
//        }
////        System.out.println(Constants.EXERCISE_MINOR);
//        Presenter.ExercisePrompt("minor");
//        String minorMuscle = reader.nextLine();
//
//        // making sure the user input is a valid minor muscle
//        while (!ExerciseConstants.ALL_MINOR_MUSCLES.contains(minorMuscle)) {
////            System.out.println(Constants.EXERCISE_MINOR_ERROR);
//            Presenter.ExercisePrompt("minor error");
//            minorMuscle = reader.nextLine();
//        }
//
////        System.out.println(Constants.EXERCISE_EQUIPMENT);
//        Presenter.ExercisePrompt("equipment1");
//        StringBuilder equipment = new StringBuilder(reader.nextLine());
//
//        // making sure the user input is a valid equipment
//        while (!ExerciseConstants.ALL_EQUIPMENTS.contains(equipment.toString())) {
////            System.out.println(Constants.EXERCISE_EQUIPMENT_ERROR_BG);
//            Presenter.ExercisePrompt("equipment error 1");
//            equipment = new StringBuilder(reader.nextLine());
//        }
//
////        System.out.println(Constants.EXERCISE_EQUIPMENT2);
//        Presenter.ExercisePrompt("equipment2");
//        String new_equipment = reader.nextLine();
//
//        // allow the user to enter as many equipments they want until they enter "None"
//        while (!new_equipment.equals("None")) {
//            while (!ExerciseConstants.ALL_EQUIPMENTS.contains(new_equipment) && !new_equipment.equals("None")) {
////                System.out.println(Constants.EXERCISE_EQUIPMENT_ERROR_AF);
//                Presenter.ExercisePrompt("equipment error 2");
//                new_equipment = reader.nextLine();
//            }
//            if (equipment.toString().contains(new_equipment)) {
////                System.out.println(Constants.EXERCISE_EQUIPMENT3);
//                Presenter.ExercisePrompt("equipment3");
//            } else if (new_equipment.equals("None")) {
//                break;
//            } else {
//                equipment.append('/').append(new_equipment);
////                System.out.println(Constants.EXERCISE_EQUIPMENT2);
//                Presenter.ExercisePrompt("equipment 2");
//            }
//            new_equipment = reader.nextLine();
//        }
//
//        return new String[]{majorMuscle, minorMuscle, equipment.toString()};
//    }
//
//    /**
//     * Helper function that lets the user input their symptoms
//     *
//     * @param reader          reads in the user input
//     * @param commandExecutor pass in the RunCommand to execute the diseaseAnalyzer
//     * @return the list of disease the user might have according to their inputted symptoms
//     */
//    public static String diseaseList(Scanner reader, RunCommand commandExecutor) throws Exception {
//        commandExecutor.resetPotentialDisease();
//        int potentialDisease;
//
//        ArrayList<String> currentSymptoms = new ArrayList<>();
//
////        System.out.println(Constants.DISEASE_START);
//        Presenter.DiseasePrompt("start");
//
//        while (true) {
//            potentialDisease = commandExecutor.executeCommandDisease(currentSymptoms);
//            if (potentialDisease <= 6) {
//                break;
//            }
//            //outputs how many potential disease client could have
//            // pass in empty array list if it is first round.
//
////            System.out.println(Constants.SYMPTOMS_DESC);
//            Presenter.DiseasePrompt("description");
//
//            Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
//            String output = analyze_results.retrieveOutput();
//            Presenter.printDiseaseOutput(output);
////            System.out.println(analyze_results.retrieveOutput()); //first time giving options
//
//            String symptoms = reader.nextLine(); //client's input of symptoms
//            currentSymptoms = new ArrayList<>();
//            if (!symptoms.equals("N/A")) {
//                symptoms = symptoms.replaceAll("[\\[\\](){}]", "");
//                String[] symptomsList = symptoms.split(",");
//                List<String> finalSymptomsList;
//                finalSymptomsList = Arrays.asList(symptomsList);
//                //convert client symptoms into a list of symptoms;
//                //add those symptoms into the current symptoms that client has
//                currentSymptoms.addAll(finalSymptomsList);
//            }
//        }
//        commandExecutor.executeCommand();
//        Presenter analyze_results = new Presenter(commandExecutor.getAnalyzer());
//
//        // returns the user's potential diseases
//        return analyze_results.retrieveOutput();
//    }
//
//    /**
//     * Helper function that lets the user input their food preference
//     *
//     * @param reader read the user input
//     * @return an array list of foodFilterCriterion
//     */
//    public static ArrayList<Object> foodPreference(Scanner reader) {
////        System.out.println(Constants.MEALPLAN_WELCOME);
//        Presenter.FoodPrompt("welcome");
////        System.out.println(Constants.LOWCARBS);
//        Presenter.FoodPrompt("low carb");
//        String lowCarb = reader.nextLine();
//        // checking to make sure the input is valid
//        while (!Objects.equals(lowCarb, "Y") && !Objects.equals(lowCarb, "N")) {
////            System.out.println(Constants.INVALID_INPUT);
//            Presenter.printInvalidPrompt("invalid");
//            lowCarb = reader.nextLine();
//        }
////        System.out.println(Constants.LOWFAT);
//        Presenter.FoodPrompt("low fat");
//        String lowFat = reader.nextLine();
//        while (!Objects.equals(lowFat, "Y") && !Objects.equals(lowFat, "N")) {
////            System.out.println(Constants.INVALID_INPUT);
//            Presenter.printInvalidPrompt("invalid");
//            lowFat = reader.nextLine();
//        }
////        System.out.println(Constants.LOWSUGAR);
//        Presenter.FoodPrompt("low sugar");
//        String lowSugar = reader.nextLine();
//        while (!Objects.equals(lowSugar, "Y") && !Objects.equals(lowSugar, "N")) {
////            System.out.println(Constants.INVALID_INPUT);
//            Presenter.printInvalidPrompt("invalid");
//            lowSugar = reader.nextLine();
//        }
////        System.out.println(Constants.VEG);
//        Presenter.FoodPrompt("veg");
//        String vegetarian = reader.nextLine();
//        while (!Objects.equals(vegetarian, "Y") && !Objects.equals(vegetarian, "N")) {
////            System.out.println(Constants.INVALID_INPUT);
//            Presenter.printInvalidPrompt("invalid");
//            vegetarian = reader.nextLine();
//        }
////        System.out.println(Constants.NUM_FOODS);
//        Presenter.FoodPrompt("num food");
//        String numFoods = reader.nextLine();
//        while (HelperConsole.isNotNum(numFoods) || Integer.parseInt(numFoods) > 25) {
////            System.out.println(Constants.INVALID_INPUT);
//            Presenter.printInvalidPrompt("invalid");
//            numFoods = reader.nextLine();
//        }
//        String[] foodCriterion = {lowCarb, lowFat, lowSugar, vegetarian};
//        ArrayList<Object> foodFilterCriterion = new ArrayList<>();
//        for (String criterion : foodCriterion) {
//            if (criterion.equals("Y")) {
//                foodFilterCriterion.add(true);
//            } else {
//                foodFilterCriterion.add(false);
//            }
//        }
//        foodFilterCriterion.add(numFoods);
//
//        return foodFilterCriterion;
//
//    }
//
//    /**
//     * Helper function that updates the user's information
//     *
//     * @param reader          reads in user's input
//     * @param commandExecutor pass in a RunCommand to help execute the command
//     * @return a msg to tell the user the update is successful
//     */
//    public static String updateUser(Scanner reader, RunCommand commandExecutor) throws Exception {
////        System.out.println(Constants.USER_UPDATE);
//        Presenter.updateUser("update user");
//        int secondCommand = Integer.parseInt(reader.nextLine());
//        RunCommand command = new RunCommand(4);
//
//        if (secondCommand == 1) { // update username
////            System.out.println(Constants.CHANGE_USERNAME);
//            Presenter.updateUser("change name");
//            String newName = reader.nextLine();
////            System.out.println(Constants.UPDATED_USERNAME);
//            Presenter.updateUser("update name");
//            commandExecutor.executeCommandUpdateInfo(secondCommand, newName);
//        }
//        //update Height
//        else if (secondCommand == 2) {
////            System.out.println(Constants.CHANGE_HEIGHT);
//            Presenter.updateUser("change height");
//            String newHeight = reader.nextLine();
////            System.out.println(Constants.UPDATED_HEIGHT);
//            Presenter.updateUser("update height");
//            commandExecutor.executeCommandUpdateInfo(secondCommand, newHeight);
//        }
//        //update Weight
//        else if (secondCommand == 3) {
////            System.out.println(Constants.CHANGE_WEIGHT);
//            Presenter.updateUser("change weight");
//            String newWeight = reader.nextLine();
////            System.out.println(Constants.UPDATED_WEIGHT);
//            Presenter.updateUser("update weight");
//            commandExecutor.executeCommandUpdateInfo(secondCommand, newWeight);
//
//        }
//        //change age
//        else if (secondCommand == 4) {
////            System.out.println(Constants.CHANGE_AGE);
//            Presenter.updateUser("change age");
//            String newAge = reader.nextLine();
////            System.out.println(Constants.UPDATED_AGE);
//            Presenter.updateUser("update age");
//            commandExecutor.executeCommandUpdateInfo(secondCommand, newAge);
//        }
//        //change gender
//        else if (secondCommand == 5) {
////            System.out.println(Constants.CHANGE_GENDER);
//            Presenter.updateUser("change gender");
//            String newGender = reader.nextLine();
////            System.out.println(Constants.UPDATED_GENDER);
//            Presenter.updateUser("update gender");
//            commandExecutor.executeCommandUpdateInfo(secondCommand, newGender);
//        }
//        //update activity level
//        else if (secondCommand == 6) {
//            String level = activityLevel(reader);
//            commandExecutor.addInfo(level, 2);
//        }
//        //update exercise preference
//        else if (secondCommand == 7) {
//            commandExecutor.addInfo(exercisePreference(reader), 3);
//        }
//        //update disease
//        else if (secondCommand == 8) {
//            // resetting the symptoms list to empty first
//            command.addInfo("", 6);
//            // overwriting the symptoms given the new input
//            return diseaseList(reader, command);
//        } else if (secondCommand == 9) {
//            // resetting the food preference to empty first
//            commandExecutor.addInfo("", 7);
//            // overwriting the food preference to the new input
//            commandExecutor.addInfo(foodPreference(reader), 5);
//
//        }
//        return SystemConstants.UPDATED_PROFILE; // this can be used for general cases 1-6
//    }
//}
