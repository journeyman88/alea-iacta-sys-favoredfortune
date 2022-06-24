/*
 * Copyright 2022 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.unknowndomain.alea.systems.favoredfortune;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.random.SingleResultComparator;
import net.unknowndomain.alea.random.dice.DicePool;
import net.unknowndomain.alea.random.dice.bag.D6;
import net.unknowndomain.alea.roll.GenericResult;
import net.unknowndomain.alea.roll.GenericRoll;

/**
 *
 * @author journeyman
 */
public class FortuneRoll implements GenericRoll
{
    private final Locale lang; 
    private final DicePool<D6> dicePool; 
    protected final Set<FortuneModifiers> mods;

    public FortuneRoll(
            Locale lang, 
            Integer skill,
            Integer field,
            Integer adv,
            Integer dis,
            Collection<FortuneModifiers> mod
    )
    {
        this.lang = lang;
        this.mods = new HashSet<>();
        this.mods.addAll(mod);
        int numDice = 0;
        if (skill != null) {
            numDice += skill;
        }
        if (field != null) {
            numDice += field;
        }
        if (adv != null) {
            numDice += adv;
        }
        if (dis != null) {
            numDice -= dis;
        }
        if (numDice < 2) {
            numDice = 2;
        }
        if (numDice > 9) {
            numDice = 9;
        }
        this.dicePool = new DicePool<>(D6.INSTANCE, numDice);
    }

    @Override
    public GenericResult getResult()
    {
        List<SingleResult<Integer>> results = dicePool.getResults();
        results.sort(new SingleResultComparator<>());
        FortuneResults res = new FortuneResults(results);
        res.setLang(lang);
        res.setVerbose(mods.contains(FortuneModifiers.VERBOSE));
        return res;
    }
    
}
