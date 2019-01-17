import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav} from 'reactstrap';


export default class Pokenavbar extends Component {

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