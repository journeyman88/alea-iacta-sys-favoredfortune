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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.unknowndomain.alea.messages.MsgBuilder;
import net.unknowndomain.alea.random.SingleResult;
import net.unknowndomain.alea.roll.LocalizedResult;

/**
 *
 * @author journeyman
 */
public class FortuneResults extends LocalizedResult
{
    private final static String BUNDLE_NAME = "net.unknowndomain.alea.systems.favoredfortune.RpgSystemBundle";
    
    private final List<SingleResult<Integer>> results;
    private final List<SingleResult<Integer>> ones;
    private final List<SingleResult<Integer>> twos;
    private final List<SingleResult<Integer>> threes;
    private final List<SingleResult<Integer>> fours;
    private final List<SingleResult<Integer>> fives;
    private final List<SingleResult<Integer>> sixes;
    private int basic = 0, critical = 0, extreme = 0, impossible = 0;
    private boolean hero = false;
    private FortuneResults prev;
    
    public FortuneResults(List<SingleResult<Integer>> results)
    {
        List<SingleResult<Integer>>  tmp = new ArrayList<>(results.size());
        tmp.addAll(results);
        this.results = Collections.unmodifiableList(tmp);
        ArrayList<SingleResult<Integer>>  tmp1 = new ArrayList<>(results.size());
        ArrayList<SingleResult<Integer>>  tmp2 = new ArrayList<>(results.size());
        ArrayList<SingleResult<Integer>>  tmp3 = new ArrayList<>(results.size());
        ArrayList<SingleResult<Integer>>  tmp4 = new ArrayList<>(results.size());
        ArrayList<SingleResult<Integer>>  tmp5 = new ArrayList<>(results.size());
        ArrayList<SingleResult<Integer>>  tmp6 = new ArrayList<>(results.size());
        for (SingleResult<Integer> t : results)
        {
            switch(t.getValue())
            {
                case 1:
                    tmp1.add(t);
                    break;
                case 2:
                    tmp2.add(t);
                    break;
                case 3:
                    tmp3.add(t);
                    break;
                case 4:
                    tmp4.add(t);
                    break;
                case 5:
                    tmp5.add(t);
                    break;
                case 6:
                    tmp6.add(t);
                    break;
            }
        }
        tmp1.trimToSize();
        this.ones = Collections.unmodifiableList(tmp1);
        tmp2.trimToSize();
        this.twos = Collections.unmodifiableList(tmp2);
        tmp3.trimToSize();
        this.threes = Collections.unmodifiableList(tmp3);
        tmp4.trimToSize();
        this.fours = Collections.unmodifiableList(tmp4);
        tmp5.trimToSize();
        this.fives = Collections.unmodifiableList(tmp5);
        tmp6.trimToSize();
        this.sixes = Collections.unmodifiableList(tmp6);
    }
    
    private void countSuccesses(int resultSize)
    {
        this.hero = this.hero || (resultSize >= 6);
        switch(resultSize)
        {            
            case 2:
                basic++;
                break;
            case 3:
                critical++;
                break;
            case 4:
                extreme++;
                break;
            case 5:
                impossible++;
                break;
        }
    }
    
    private void resetSuccesses()
    {
        basic = 0;
        critical = 0;
        extreme = 0;
        impossible = 0;
        hero = false;
    }
            

    @Override
    protected void formatResults(MsgBuilder messageBuilder, boolean verbose, int indentValue)
    {
        String indent = getIndent(indentValue);
        this.resetSuccesses();
        this.countSuccesses(this.ones.size());
        this.countSuccesses(this.twos.size());
        this.countSuccesses(this.threes.size());
        this.countSuccesses(this.fours.size());
        this.countSuccesses(this.fives.size());
        this.countSuccesses(this.sixes.size());
        if (hero)
        {
            messageBuilder.append(indent).append(translate("fortune.results.hero")).appendNewLine();
        }
        else
        {
            if (basic > 0)
            {
                messageBuilder.append(indent).append(translate("fortune.results.basic", basic)).appendNewLine();
            }
            if (critical > 0)
            {
                messageBuilder.append(indent).append(translate("fortune.results.critical", critical)).appendNewLine();
            }
            if (extreme > 0)
            {
                messageBuilder.append(indent).append(translate("fortune.results.extreme", extreme)).appendNewLine();
            }
            if (impossible > 0)
            {
                messageBuilder.append(indent).append(translate("fortune.results.impossible", impossible)).appendNewLine();
            }
            if ((basic <= 0) && (critical <= 0) && (extreme <= 0) && (impossible <= 0))
            {
                messageBuilder.append(indent).append(translate("fortune.results.failure")).appendNewLine();
            }
        }
        if (verbose)
        {
            messageBuilder.append(indent).append("Roll ID: ").append(getUuid()).appendNewLine();
            if (!results.isEmpty())
            {
                messageBuilder.append(indent).append(translate("fortune.results.results")).append(" [ ");
                for (SingleResult<Integer> t : results)
                {
                    messageBuilder.append("( ").append(t.getLabel()).append(" => ").append(t.getValue()).append(" ) ");
                }
                messageBuilder.append("]\n");
            }
            if (prev != null)
            {
                messageBuilder.append(indent).append(translate("fortune.results.prevResults")).append(" {\n");
                prev.formatResults(messageBuilder, verbose, indentValue + 4);
                messageBuilder.append(indent).append("}\n");
            }
        }
    }

    public FortuneResults getPrev()
    {
        return prev;
    }

    public void setPrev(FortuneResults prev)
    {
        this.prev = prev;
    }

    public List<SingleResult<Integer>> getResults()
    {
        return results;
    }

    @Override
    protected String getBundleName()
    {
        return BUNDLE_NAME;
    }

    /**
     * @return the ones
     */
    public List<SingleResult<Integer>> getOnes() {
        return ones;
    }

    /**
     * @return the twos
     */
    public List<SingleResult<Integer>> getTwos() {
        return twos;
    }

    /**
     * @return the threes
     */
    public List<SingleResult<Integer>> getThrees() {
        return threes;
    }

    /**
     * @return the fours
     */
    public List<SingleResult<Integer>> getFours() {
        return fours;
    }

    /**
     * @return the fives
     */
    public List<SingleResult<Integer>> getFives() {
        return fives;
    }

    /**
     * @return the sixes
     */
    public List<SingleResult<Integer>> getSixes() {
        return sixes;
    }

}
