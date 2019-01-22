import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Pokenavbar from './Navbar';



export default class AboutTheGame extends Component {


    render() {
        return (
        <div>
            <Pokenavbar/>
                <Container name="mainPage" style={{backgroundColor: 'white', minHeight: '700px', marginTop: '30px', boxShadow: '5px 5px #c9cacc'}}>  
                    <center>
                    <h1 style={{marginTop: '40px'}}>About the Game</h1>
                </center> 
                </Container>
        </div>
        );  
    }
}