# Container

![diagram](https://www.plantuml.com/plantuml/svg/0/bLJBRbf13DqZyGykAv0Qs7JLLGX9yn0aA91MNI5phqEccbVdGPjM_PYgYwZhgb_0ZzKp5suWJL8LNOaPZ-Vus3wUVH_GXQWa38mEA3IvPkE5q8MC9S5D2DQ_xlKSVkekHBY9y-Z95Un9EdGBetfOEe5wAQEDlS6hlR547Qv6mvx4G3wmQLRZTYs7QJQQZM7__SNLTFRkT7eo6nuT7vqVjZl9FYRdZMvZgOGMFZWiZNi9hVxsliM62myxFkHXGOvq8L210SzsKjZgr8Y55CoN-EgWMcKKM9l16oaA4H0AuvmeqOCsW9QD6CJIR05PZXNMjCQRDN2Xk2fBiRep5N3j1bm5pSV6rnw-3bVosf9y6AhPcDoc16P7dqDxZhemZ7cG_XCu4r62EN-CZ0eM5wWu1mE9TKtVqmU4Qn8X0sx1ARIUGOwDMKY24UyIvXlZD1K42D8KACKNNDsjVXX8HkzNtxd37CdXRXgR9FqEwGCJTOdkjhtk0iDF7Uf9AeDhTU1him7ygqNHzZRvJ8HEZCQu8EZRTJsCJkpES8c-SC86Jb319M4Hjba7arBcxpgo0epc14eiMHEbWVlGWEPlrLZka29XgWv6qBYaHJhhffnsUUPKHcOkP6Ru5BEzyzH8sYRuRrvh8NgWU-dm3fdeSlNJ9tfHGIcyDLuadO1Q_LgIV8HcEUV8XnXmZZwpoCf926KMd2E95Pjqs7SAlv164FhQy9hx_fkgU3wcnXkMoqPMl79KvZ4JlA5GF18VhKYuuzF7sfZBDB6EcmsZjuFdYeGxHTfkL5G82VziSd7UEvbEnzrAW8d79ScAmLheKr8fPjHC96iwCzbSd6perzSsu_Q_ztBLXpnmaQU4W98g0kPBXmU3NNvPy5MPhZm-3QoUT_kMC9DV_RXbXIRVJdMmzLWzF7hmn5Opk0vJHsWszhdw-St-0m00)

**Level 2: Container diagram**

Once you understand how your system fits in to the overall IT environment, a really useful next step is to zoom-in to the system boundary with a Container diagram. A "container" is something like a server-side web application, single-page application, desktop application, mobile app, database schema, file system, etc. Essentially, a container is a separately runnable/deployable unit (e.g. a separate process space) that executes code or stores data.

The Container diagram shows the high-level shape of the software architecture and how responsibilities are distributed across it. It also shows the major technology choices and how the containers communicate with one another. It's a simple, high-level technology focussed diagram that is useful for software developers and support/operations staff alike.

**Scope**: A single software system.

**Primary elements**: Containers within the software system in scope.
Supporting elements: People and software systems directly connected to the containers.

**Intended audience**: Technical people inside and outside of the software development team; including software architects, developers and operations/support staff.

**Notes**: This diagram says nothing about deployment scenarios, clustering, replication, failover, etc.