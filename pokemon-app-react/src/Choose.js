import React, { Component } from 'react';
import './App.css'
import StarterPokemon from './StarterPokemon';


import { Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button, FormGroup } from 'reactstrap';


export default class Choose extends Component {
  constructor(props) {
    super(props);

    this.state = {
    }
  }


  render() {
    return (
      <div className="col-game">
        <div className="row-game">

          <StarterPokemon close={this.props.close} change={this.props.change} pokemon={{ name: "bulbasaur", id: 1, types: ["POISON", "GRASS"], level: 5 }} pokestyle={{ color: 'black', backgroundColor: 'rgb(11, 221, 57)' }} />

          <StarterPokemon close={this.props.close} change={this.props.change} pokemon={{ name: "squirtle", id: 7, types: ["WATER"], level: 5 }} pokestyle={{ color: 'black', backgroundColor: 'rgb(11, 197, 221)' }} />

          <StarterPokemon close={this.props.close} change={this.props.change} pokemon={{ name: "charmander", id: 4, types: ["FIRE"], level: 5 }} pokestyle={{ color: 'black', backgroundColor: 'rgb(255, 123, 0' }} />
        </div>

        <Container className="menu main-game-panel">
          <h1>Choose your starter Pokémon!!!</h1>
        </Container>
      </div>
    );
  }
}