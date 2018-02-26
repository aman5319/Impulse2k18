package com.amidezcod.impulse2k18.modal;

import com.amidezcod.impulse2k18.adapter.CardPagerAdapter;
import impulse2k18.R;

/**
 * Created by amidezcod on 22/2/18.
 */

public class DataForEvents {

    public static CardPagerAdapter enterData(CardPagerAdapter mCardAdapter) {

        EventDetailsInfo eventDetailsInfo = new EventDetailsInfo("Unleash your coding weapons and claim your right to the throne. Let your instincts and skills guide you to the final round and take on the challenging quests of programming. ",
                "1. Individual or two members per team.\n 2. Coding can be done in any language. \n 3. The event has three rounds. \n 4. Prelims Er Debugging: \n\ta.Time limit - 30 minutes. \n\tb. Top 10 will advance in next round.\n 5. Finals: a. Time Limit 90 minutes. \n\t " +
                        "   b. Problems of varying complexity will be given for coding. \n\t " +
                        "\tc. Judges decision will be final."
                , "100", "Cash Prize \n Certificate", "3rd Floor CSE Dept ",
                new String[]{"Aman pandey +9121" , "Ram Poudel +213"}, "http://impulse2k18.tk/register.html#coding");


        mCardAdapter.addCardItem(
                new CardItem(R.drawable.webdesign, "#Web Design", "Design is Thinking made visible", eventDetailsInfo));
        mCardAdapter.addCardItem(
                new CardItem(R.drawable.coding, "#Coding", "Programming without bugs is not fun", eventDetailsInfo));

        mCardAdapter.addCardItem(
                new CardItem(R.drawable.debate, "#Debate", "Don't raise your voice \n Improve your Argument", eventDetailsInfo));

        mCardAdapter.addCardItem(
                new CardItem(R.drawable.entertainment_quiz, "#Entertainment", "Entertainment\nEntertainment\nEntertainment", eventDetailsInfo));

        mCardAdapter.addCardItem(
                new CardItem(R.drawable.dance, "#FootLoose", "When I dance, I forget everything just feel happy.\n", eventDetailsInfo));
        mCardAdapter.addCardItem(
                new CardItem(R.drawable.game, "#Gaming", "I'm a gamer because i don't have have life i've chosen to have many", eventDetailsInfo));

        mCardAdapter.addCardItem(
                new CardItem(R.drawable.it_quiz, "#IT Quiz", "I'm hosting a quiz show, but I never considered myself a game show host.", eventDetailsInfo));
        mCardAdapter.addCardItem(
                new CardItem(R.drawable.kannada, "#Namma Baashe Kannada", "Faith in the words\n pride in our souls", eventDetailsInfo));

        mCardAdapter.addCardItem(
                new CardItem(R.drawable.movie_making, "#Movie Making", "Shoot Movies \n Not People", eventDetailsInfo));

        mCardAdapter.addCardItem(
                new CardItem(R.drawable.photo, "#Photography", "Every picture has a \n story to tell", eventDetailsInfo));
        mCardAdapter.addCardItem(
                new CardItem(R.drawable.treasure_hunt, "#Treasure Hunt", "Not all treasure is\n silver and gold mate ", eventDetailsInfo));

        mCardAdapter.addCardItem(
                new CardItem(R.drawable.start_of_impulse, "#Buzzinga", "Star of the Impulse", eventDetailsInfo));
        mCardAdapter.addCardItem(
                new CardItem(R.drawable.start_of_impulse, "#Buzzinga", "Star of Impulse", eventDetailsInfo));
        mCardAdapter.addCardItem(
                new CardItem(R.drawable.start_of_impulse, "#Buzzinga", "Star of Impulse", eventDetailsInfo));
        return mCardAdapter;
    }
}
