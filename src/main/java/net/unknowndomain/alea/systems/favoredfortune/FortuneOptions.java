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
import java.util.Set;
import net.unknowndomain.alea.systems.RpgSystemOptions;
import net.unknowndomain.alea.systems.annotations.RpgSystemData;
import net.unknowndomain.alea.systems.annotations.RpgSystemOption;


/**
 *
 * @author journeyman
 */
@RpgSystemData(bundleName = "net.unknowndomain.alea.systems.favoredfortune.RpgSystemBundle")
public class FortuneOptions extends RpgSystemOptions
{
    @RpgSystemOption(name = "skill", shortcode = "s", description = "fortune.options.skill", argName = "skillValue")
    private Integer skill;
    @RpgSystemOption(name = "field", shortcode = "f", description = "fortune.options.field", argName = "fieldValue")
    private Integer field;
    @RpgSystemOption(name = "advantages", shortcode = "a", description = "fortune.options.advantages", argName = "advantages")
    private Integer advantages;
    @RpgSystemOption(name = "disadvantages", shortcode = "d", description = "fortune.options.disadvantages", argName = "disadvantages")
    private Integer disadvantages;
    
    @Override
    public boolean isValid()
    {
        return !(isHelp());
    }

    public Integer getSkill()
    {
        return skill;
    }

    public Integer getField()
    {
        return field;
    }

    public Integer getDisadvantages()
    {
        return disadvantages;
    }

    public Integer getAdvantages()
    {
        return advantages;
    }

    public Collection<FortuneModifiers> getModifiers()
    {
        Set<FortuneModifiers> mods = new HashSet<>();
        if (isVerbose())
        {
            mods.add(FortuneModifiers.VERBOSE);
        }
        return mods;
    }
    
}