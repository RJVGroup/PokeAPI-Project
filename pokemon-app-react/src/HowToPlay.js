import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';

import Pokenavbar from './Navbar';


export default class HowToPlay extends Component {


    render() {
        return (
        <div >
            <Pokenavbar/>
            <Container classname="menu">
                <center>
                    <h1 style={{marginTop: '40px'}}>How To Play</h1>
                </center>
                </Container>
        </div>
        );  
    }
}