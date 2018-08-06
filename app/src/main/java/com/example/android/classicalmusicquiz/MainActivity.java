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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String JOGO_FINALIZADO = "jogo_finalizado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView maiorPontuacaoView = (TextView) findViewById(R.id.highscoreText);

        // Pegar a maior e menor pontuação do jogador
        int highScore = QuizUtils.getHighScore(this);
        int maxScore = Quiz.getAllSampleIDs(this).size() - 1;

        // Setar a maior pontuação como texto
        String maiorPontuacaoTxt = getString(R.string.high_score, highScore, maxScore);
        maiorPontuacaoView.setText(maiorPontuacaoTxt);

        // Quando o jogo terminar, mostrar a tela com detalhes do jogo
        if(getIntent().hasExtra(JOGO_FINALIZADO)){
            TextView jogoFinalizadoView = (TextView) findViewById(R.id.gameResult);
            TextView suaPontuacaoView = (TextView) findViewById(R.id.resultScore);

            Integer yourScore = QuizUtils.getCurrentScore(this);
            String yourScoreText = getString(R.string.score_result, yourScore, maxScore);
            suaPontuacaoView.setText(yourScoreText);

            jogoFinalizadoView.setVisibility(View.VISIBLE);
            suaPontuacaoView.setVisibility(View.VISIBLE);
        }
    }

    public void newGame(View view) {
        Intent quizTela = new Intent(this, QuizActivity.class);
        startActivity(quizTela);
    }
}
