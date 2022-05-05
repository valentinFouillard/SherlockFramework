# SherlockFramework

Sherlock is framework that allows to model erroneous decision making of a human agent and to compute the possible beliefs states that could explain his decisions. Moreover this framework offers a range of cognitive explanations based on the  cognitive bias litterature to provide insight into the plausibility belief state.

# Setup

Sherlock uses the Z3 SMT solver to compute each possible belief state of the agent. Therefore before even trying to run Sherlock you must install Z3 which you can find on the [Z3 Git page](https://github.com/Z3Prover/z3). 

# Run 

Sherlock has 3 main parameters :

- **Input** : is the *text* file that contains the model of the erroneous decision making.
- **Output** : is the *XML* file that will contains the output with the belief states and the explanations
- **Explanations** : the third is a list of cognitive explanations that will be used to explain each belief state. Possible values are :
  - `Anchor` for the anchorig bias ;
  - `Avoid` for the attentional avoidance bias ;
  - `Confirmation` for the confirmation bias ;
  - `Control` for the illusion of control ;
  - `Correlation` for the illusion of correlation ;
  - `Desengagement` for the attentional desengagement bias ;
  - `Facilitation` for the attentional facilitation bias ;
  - `Loss` for the loss aversion ;
  - `Optimism` for the optimism bias ;
  - `Overconfidence` for the overconfidence ;
  - `Risk` for the risk aversion ;
  - `Truth` for the illusion of truth ;
  - `Wishful` for the wishful thinking ; 
  - `All` is a shortcut to run Sherlock with all possibles explanations. 

## Example
The command line :

```
Sherlock ressources/Test/confirmationV0.txt result.XML Confirmation Facilitation
```

will launch Sherlock on the model [confirmationV0](ressources/Test/confirmationV0.txt) with the explanation of the confirmation bias and the attentional facilitation and return the belief states in the result.XML file.

# How to model
## Logic syntax
The Sherlock framework uses modeling through  the definition of logical propositions and logical formulas to represent respectively the beliefs and the reasoning rules of the agent. Each logical proposition is indexed by a time step to indicate at which time step the statement is true. For example `cloud(2)` say that there is cloud at step 2. A reasoning rules is then a logical formula that uses the logical propositions indexed by the variable `t`. 

For example :
```
(alarm(t) and (not reserveFuel(t))) => outFuel(t+1)
```
say that if there is an alarm and there is not a reserve of fuel at step t then the agent will be out of fuel at next step.

In addition to this, there exists two spectifs rules using actions :
- the effects of an action `
```
turnOff(t) :: (not light(t+1))
```
the action to turn off the light at step t causes the light to be off at next step.
- the precondtions of an action
```
[not locked(t)]open(t)
```
the action to open a door at step t can only be performed if the door is not locked.

## Model syntax
You can find several examples of modelisation in the directory [Test](ressources/Test/). Each file of modelisation must contains 10 sections :
- **Limits** : contains the time step limit of the model.
```
===Limits===
t = 2
```
mean that the model contains 2 time steps.
- **Facts** : contains all the names of logical propositions that are not actions and used in the model.
```
===Facts===
alarm 
danger 
greenLight
```
- **Actions** : contains all the names of logical propositions which represent the actions that the agent can performed.
```
===Actions===
goOn
stop
```
- **PermanentRules** : contains the reasoning rules of the agent that he cannot ignore (can be empty).

- **Rules** : the reasoning rules are divided into two categories :
  - **Strong** : a list of logical formulas for which the agent believes that they always true
  ```
  ===Strong===
  [not danger(t)]goOn(t)
  ```
  - **Weak** : a liste of logical formulas for which the agent believes that the are often true
  ```
  ===Weak===
  alarme(t) => danger(t)
  greenLight(t) => (not danger(t))
  ```
- **Desires** : a list of logical proposition indexed by the varaible ``t`` that need to be true at each time step. For example if ``not late(t)`` is in the desires then for each time step the proposition not late need to be true (can be empty).
```
===Desires===
not danger(t)
```
- **InitialWorldBeliefs** : which propositions the agent believe to be true at step 0 
```
===InitialWorldBeliefs===
alarm(0)
```
- **Observations** : which propositions can be observed by the agent at each time step. If a proposition is not in the observations then it means that the agent could not observe the proposition and the negation of the proposition.
```
===Observations===
1:alarme(1)
2:alarme(2),greenLight(2)
```
- **RunTime** : which action is performed at each time step (only one action per time step)
```
===RunTime===
1:goOn(1)
2:goOn(2)
```

# Output of the framework

The output of the framework is an tree-like structure where a node ``State`` contains what the agent ignore (``MCS``) and the explanations possible (``Bias``). Each node ``State`` from a time step ``t`` contains all the next states for the step ``t+1`` and so on. Therefore a path in the tree of state represents a possible explanation of the decision making of the agent.  
