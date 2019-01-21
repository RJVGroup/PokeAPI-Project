import React, {Component} from 'react';
import Pokenavbar from './Navbar';
import App from './App';
import {withRouter } from 'react-router-dom';


class Home extends Component {


    render() {
        return (<div>
                        <Pokenavbar/>
                        <App/>
                                </div>
        );  
    }
}
export default withRouter(Home)