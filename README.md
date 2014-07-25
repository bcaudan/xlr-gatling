xlr-gatling
===========

# Installation

Download Gatling 1.5.6:

    http://gatling-tool.org/


Replace user_files directory with this repository: 

    rm -rf $GATLING_HOME/user-files && git clone https://github.com/bcaudan/xlr-gatling $GATLING_HOME/user-files

# Usage

You need a XLR running instance on http://localhost:5516 with a fresh repository.

Then, insert the benchmark data:

    ./$GATLING_HOME/user-files/init-xl-release-for-benchmark.sh

You can launch the simulation with:

    $GATLING_HOME/bin/gatling.sh -s simulations.xlrelease.XLReleaseSimulation
