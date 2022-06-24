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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import net.unknowndomain.alea.icon.AleaIcon;
import net.unknowndomain.alea.icon.AleaImage;
import net.unknowndomain.alea.icon.IconSolver;
import net.unknowndomain.alea.systems.RpgSystemDescriptor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author journeyman
 */
public class Household2Solver extends IconSolver
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Household2Solver.class);
    private static final RpgSystemDescriptor DESC = new FortuneSystem();
    
    @Override
    public boolean checkNamespace(String namespace)
    {
        return Objects.equals(DESC.getShortcut(), namespace);
    }

    @Override
    protected AleaImage loadImage(AleaIcon icon)
    {
        AleaImage retVal = null;
        AleaImage image = new AleaImage(icon);
        image.setFileName(icon.getIconId() + ".png");
        image.setMimeType("image/png");
        try (
                InputStream is  = Household2Solver.class.getResourceAsStream("/net/unknowndomain/alea/systems/favoredfortune/household/" + image.getFileName());
                ByteArrayOutputStream os  = new ByteArrayOutputStream();
            ) {
            IOUtils.copy(is, os);
            image.setData(os.toByteArray());
            retVal = image;
        } 
        catch (IOException ex)
        {
            LOGGER.error(null, ex);
        }
        return retVal;
    }
    
}
