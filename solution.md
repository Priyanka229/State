# Solution: {Priyanka229}

Date: {Feb 21st 2021}

## Summary

This project is based on MVVM architecture. I have created single activity comprising 2 fragments
with the single viewModel shared among them.

When the app launches, I am notifying viewModel about an action and then viewModel is posting
corresponding event to an open StateListFragment.

Once StateListFragment is successfully loaded, it is also notifying viewModel about the loading
process and then viewModel makes server call to fetch the state list.StateListFragment is also
observing the StateListLiveData so once the viewModel emits the list, StateListFragment will update
itself accordingly.

When user clicks on any given state, it leads to notification of viewModel about the action.
This is followed by viewModel emitting eventto open the StateDetailFragment which is observed by MainActivity.
Then MainActivity opens the StateDetailFragment by sending the state details.
On StateDetailFragment I am showing the all the state details.


## Requirements

I have used below components to build this project.
1. Coroutines (To schedule network-calls/heavy-operation on background thread)
2. AndroidViewModel (To implement MVVM pattern)
3. Retrofit (To make network calls)
4. Espresso (UI test)
5. Mockito (Mocking the object for test)
6. Junit (Unit test)
7. Data binding (It is mainly used for binding data with view and I mostly use this to ignore findViewById() calls)

## Ambiguity Notes

1. I have created Network Logger class called ApptentiveNetworkLoggingIntercepter.kt to debug the network calls. Currently it is empty but we can update it according to our need.
2. I am assuming list will be in sorted order so I am showing it without sorting.

