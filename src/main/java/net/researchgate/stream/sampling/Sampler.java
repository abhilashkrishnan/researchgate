/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the GNU Lesser General Public License version 2.1 (LGPLv2.1) (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.gnu.org/licenses/old-licenses/lgpl-2.1.en.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.researchgate.stream.sampling;

/**
 * @author Abhilash Krishnan
 * @since 06-04-2017
 * Abstract class for random sampling implementation
 */

public abstract class Sampler {
    /**
     * Sample Size
     */
    protected int size;

    public Sampler(int size) {
        this.size = size;
    }

}
