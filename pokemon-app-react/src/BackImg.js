import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class BackImg extends Component {
    constructor(props) {
      super(props);
    };
    render() {
      return (
          <div className="pokemonimg">
        <img src={"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/"+this.props.id+".png"}/></div>
      );
    }
  }