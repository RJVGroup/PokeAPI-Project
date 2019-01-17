import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class Home extends Component {


    render() {
        return (
            <div>
                <Navbar color="danger" dark expand="md">
                    <NavbarBrand style={{marginLeft: '10px'}} class="logo" href="/">PokéAPI Game</NavbarBrand>
                    <Nav className="ml-auto" navbar>
                        <NavItem>
                            <NavLink href="/aboutthegame">About the Game</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="/howtoplay">How to Play</NavLink>
                        </NavItem>
                    </Nav>
                </Navbar>
            
            </div>
        );
    }
}