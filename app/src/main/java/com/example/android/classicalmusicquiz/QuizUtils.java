/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.classicalmusicquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class QuizUtils {

    private static final String PONTUACAO_CORRENTE_CHAVE = "pontuacao_corrente";
    private static final String PONTUACAO_MAIS_ALTA_CHAVE = "maior_pontuacao";
    private static final String JOGO_FINALIZADO = "jogo_finalizado";
    private static final int QTD_RESPOSTAS = 4;

    /**
     * Generates an ArrayList of Integers that contains IDs to QTD_RESPOSTAS samples. These samples
     * constitute the possible answers to the question.
     * @param remainingSampleIDs The ArrayList of Integers which contains the IDs of all
     *                           samples that haven't been used yet.
     * @return The ArrayList of possible answers.
     */
    static ArrayList<Integer> generateQuestion(ArrayList<Integer> remainingSampleIDs){

        // Shuffle the remaining sample ID's.
        Collections.shuffle(remainingSampleIDs);

        ArrayList<Integer> answers = new ArrayList<>();

        // Pick the first four random Quiz ID's.
        for(int i = 0; i < QTD_RESPOSTAS; i++){
            if(i < remainingSampleIDs.size()) {
                answers.add(remainingSampleIDs.get(i));
            }
        }

        return answers;
    }

    /**
     * Helper method for getting the user's high score.
     * @param context The application context.
     * @return The user's high score.
     */
    static int getHighScore(Context context){
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return mPreferences.getInt(PONTUACAO_MAIS_ALTA_CHAVE, 0);
    }

    /**
     * Helper method for setting the user's high score.
     * @param context The application context.
     * @param highScore The user's high score.
     */
    static void setHighScore(Context context, int highScore){
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(PONTUACAO_MAIS_ALTA_CHAVE, highScore);
        editor.apply();
    }

    /**
     * Helper method for getting the user's current score.
     * @param context The application context.
     * @return The user's current score.
     */
    static int getCurrentScore(Context context){
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        return mPreferences.getInt(PONTUACAO_CORRENTE_CHAVE,0);
    }

    /**
     * Helper method for setting the user's current score.
     * @param context The application context.
     * @param currentScore The user's current score.
     */
    static void setCurrentScore(Context context, int currentScore){
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(PONTUACAO_CORRENTE_CHAVE, currentScore);
        editor.apply();
    }

    /**
     * Picks one of the possible answers to be the correct one at random.
     * @param answers The possible answers to the question.
     * @return The correct answer.
     */
    static int getCorrectAnswerID(ArrayList<Integer> answers){
        Random r = new Random();
        int answerIndex = r.nextInt(answers.size());
        return answers.get(answerIndex);
    }

    /**
     * Checks that the user's selected answer is the correct one.
     * @param correctAnswer The correct answer.
     * @param userAnswer The user's answer
     * @return true if the user is correct, false otherwise.
     */
    static boolean userCorrect(int correctAnswer, int userAnswer){
        return userAnswer == correctAnswer;
    }


    /**
     * Helper method for ending the game.
     * @param context The application method.
     */
    static void endGame(Context context){
        Intent endGame = new Intent(context, MainActivity.class);
        endGame.putExtra(JOGO_FINALIZADO, true);
        context.startActivity(endGame);
    }
}
