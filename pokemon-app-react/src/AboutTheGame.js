import React, {Component} from 'react';
import Pokenavbar from './Navbar';


export default class AboutTheGame extends Component {


    render() {
        return (
        <div>
            <Pokenavbar/>;
                <center>
                    <h1 style={{marginTop: '40px'}}>About the Game</h1>
                </center>
        </div>
        );  
    }
}