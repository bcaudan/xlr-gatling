xlr-gatling
===========

# Installation

Download Gatling 1.5.6:

    http://gatling-tool.org/


Clone repository as: 

    rm -rf $GATLING_HOME/user-files && git clone https://github.com/bcaudan/xlr-gatling $GATLING_HOME/user-files

# Usage

You need a XLR running instance on http://localhost:5516 with a fresh repository.

You can launch the simulation with:

    $GATLING_HOME/bin/gatling.sh -s simulations.xlrelease.XLReleaseSimulation
