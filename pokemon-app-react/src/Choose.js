import React, { Component } from 'react';
import './App.css'
import FrontImg from './FrontImg';
import StarterPokemon from './StarterPokemon';


import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class Choose extends Component {
  constructor(props) {
    super(props);
  this.state = {
}
}

  render() {
    return (
      <div className="col-game">
      <div className="row-game"onClick={this.props.close}>  
       
      <StarterPokemon pokemon="bulbasaur" pokestyle={{backgroundColor: 'rgb(11, 221, 57)'} }/>
  
      <StarterPokemon pokemon="squirtle"  pokestyle={{backgroundColor: 'rgb(11, 197, 221)'}}/>
        
      <StarterPokemon pokemon="charmander" pokestyle={{backgroundColor: 'rgb(255, 123, 0'}}/>
            
      </div>
      <Container className= "menu main-game-panel">
        Choose your starter pokemon!!!
        </Container>
      </div>
    );
  }
}