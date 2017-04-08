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

import java.util.List;

/**
 * @author Abhilash Krishnan
 * @since 06-04-2017
 * Entry point of Stream Sampling application. Takes the inputs from java command line arguments.
 * The random sampling of the stream is implemented using Min Heap Sampling and Reservoir Sampling
 * Algorithms.
 */

public class Application {

    public static void main (String args[]) {

        if(args != null && args.length == 2) {
            String stream = args[0];
            int sampleSize = Integer.parseInt(args[1]);

            if (sampleSize <= 0 || sampleSize > stream.length()) {
                System.out.println("Invalid Sample Size. Operation cannot be performed.");
                System.exit(0);
            }

            System.out.println("Stream: " + stream);

            System.out.println("");

            System.out.println("Algorithm: MinHeap Sampler");

            int[] cstream = new int[stream.length()];
            int i = 0;
            for (char c : stream.toCharArray()) {
                cstream[i] = (int) c;
                i++;
            }


            MinHeapSampler mhs = new MinHeapSampler(sampleSize);
            mhs.createHeap();
            mhs.sample(cstream);
            mhs.display();

            System.out.println("");

            System.out.println("Algorithm: Reservoir Sampler");

            List<Integer> sample = stream.chars().boxed().collect(new ReservoirSampler<>(sampleSize));
            System.out.print("Random Sample: ");
            for (Integer s : sample) {
                System.out.print((char)s.intValue());
            }
            System.out.println("");
        } else {
            System.out.println("No inputs are available.");
            System.exit(0);
        }
    }
}
