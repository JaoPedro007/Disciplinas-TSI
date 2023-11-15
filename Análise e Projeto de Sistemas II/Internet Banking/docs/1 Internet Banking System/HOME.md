# 1 Internet Banking System

![diagram](https://www.plantuml.com/plantuml/svg/0/fLNDRjim3BuNw3jmEGmfq2QNdNPQcmRzGTCQTRfsfu2MbLYRVWn9TbeCUvuzo5vib1mxJfkXX-MGsEH7ykCdKldYF5fVANbuy45e9gkSG-5zwJwFnnRNevNmHPLLZbjcjEVQZvXHOyojG5tBggp6aqydYKJj7sOtOucUEq-cnOJGA3Itep8cFpouEVrszp1VF5xDBnStqulfxVdmADWJRftHmp9Znp2OI44r-80Uxu0rB-2C54nu1EE0YDJYpszwAhaqW2Mvq8lQ38wsgT1GV6A92kR60hNKXZM9oyv5V3MvTnAtvNBZUaanLnHxcYkXXVCsH8Ta5znopGI2Dxa10uwSN648J5_2usBwx8VS4NHweb18SD4SOkS51q4DMeqIPe9PuypIm_IP5QXN77W_OFGgfSf2pXbflBIeE9oX_Y7q2j8kVUeDzGae9PWbU2h6o03P1aY1nYhIpcZ0p5GUMEMyKTJkCI1ZfjAUdgXLxP056Brnp_f4pWYKetqPYiZcAZR3VKjcS0G_3m-0Fjqu3DT8m4UUmMcvEJkZ0zllM2EWpY4jRQ0t-pe9vdCkHKsK8dywD2yOR2Onedsd8PLjMtFq9JcKI2BYjanalil6bO5Esk2JWDz3op4hogOSdKibCPvyOaqjyet0UsaiAnrrGody2mt8Lc2emyGKN5XOanIPDMkQlptyLA0tCvcGaLce-uGAIQ5T2Y254fxduAhC4GNZ_uEIQkfjVd9U2yRtSCkpS3xeCKCND_QUIsoIGsk6b1LX7RRZ6AuIi7mLboWENsyGZw50Lr0RM14LxJS70SpoFBoYR6QJEmVIh7e3kKiDIn6MDRdwzvJv_fHrGhuZMD08uJgzknrVpkS9K8LOlQbF_JULuxgdTBEupGwxAC1idhHKEsYpnW-EHyWJtQ4XSVe65yM-vqWfFyBQqbdJIiJhxFhyRDA7XmLhnhhTdRutUXfvsg9TEpjLrJkuNw7h08mNTzpg5jXXEdV39_fRSxYZtvhZBRi77PAerzOWS_EtiQDrq8KGNyWM_th-0W00)

**Level 2: Container diagram**

Once you understand how your system fits in to the overall IT environment, a really useful next step is to zoom-in to the system boundary with a Container diagram. A "container" is something like a server-side web application, single-page application, desktop application, mobile app, database schema, file system, etc. Essentially, a container is a separately runnable/deployable unit (e.g. a separate process space) that executes code or stores data.

The Container diagram shows the high-level shape of the software architecture and how responsibilities are distributed across it. It also shows the major technology choices and how the containers communicate with one another. It's a simple, high-level technology focussed diagram that is useful for software developers and support/operations staff alike.

**Scope**: A single software system.

**Primary elements**: Containers within the software system in scope.
Supporting elements: People and software systems directly connected to the containers.

**Intended audience**: Technical people inside and outside of the software development team; including software architects, developers and operations/support staff.

**Notes**: This diagram says nothing about deployment scenarios, clustering, replication, failover, etc.