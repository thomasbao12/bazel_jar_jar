/**
 * Copyright 2007 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.johnynek.jarjar.util;

import java.io.IOException;

public class JarProcessorChain implements JarProcessor
{
    private final JarProcessor[] chain;

    public JarProcessorChain(JarProcessor[] chain)
    {
        this.chain = chain.clone();
    }

    /**
     * @param struct
     * @return <code>true</code> if the entry has run the complete chain
     * @throws IOException
     */
    public boolean process(EntryStruct struct) throws IOException {

        for (JarProcessor processor : chain) {
            if (!processor.process(struct)) {
                return false;
            }
        }
        return true;
    }
}

