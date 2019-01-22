import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class Battle extends Component {
    constructor(props) {
      super(props);
    this.state = {
    };
  }
  
  componentDidMount() {
   
    }
  
    render() {
      return (
  
        <div className='main-game'>  
         <Container className="menu main-game-panel">
  
             </Container> 
             <Container className="menu main-game-panel" >      
             <button className=" main-game-panel">Fight</button>
             <button className=" main-game-panel">Pokemon</button>
             <button className=" main-game-panel">Bag</button>
             <button className=" main-game-panel">Run</button>
             </Container> 
        </div>  
      );
    }
  }