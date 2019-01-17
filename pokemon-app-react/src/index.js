import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import './bootstrap.min.css';
import App from './App';
import AboutTheGame from './AboutTheGame';
import HowToPlay from './HowToPlay';
import * as serviceWorker from './serviceWorker';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

ReactDOM.render( <BrowserRouter>
    <Switch>
        <Route exact path ="/" component={App} />
        <Route path = "/aboutthegame" component={AboutTheGame}/>
        <Route path = "/howtoplay" component={HowToPlay} />
    </Switch>
    </BrowserRouter>,
 document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
