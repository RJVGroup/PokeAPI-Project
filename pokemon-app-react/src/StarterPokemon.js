import React, { Component } from 'react';
import './App.css'
import FrontImg from './FrontImg';
import { ButtonGroup, Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button, FormGroup } from 'reactstrap';


export default class StarterPokemon extends Component {
  constructor(props) {
    super(props);
    this.chooseStarter = this.chooseStarter.bind(this);
    this.state = {
      starter: this.props.pokemon,
      disabled: false

    }
  }




  chooseStarter() {
    this.setState({ disabled: true })
    fetch('/api/player/add-starter-pokemon/' + this.state.starter.name, { method: 'POST' })
      .then(response => response.json())
      .then(data => {
        if (data === true) {
          this.props.close(); this.props.change(0)
        }
      })
  }

  render() {
    return (
      <Button className="menu main-game-panel" onClick={(e) => this.chooseStarter(e)} style={this.props.pokestyle} disabled={this.state.disabled}>
        <FrontImg id={this.state.starter.id} />
        NAME: {this.state.starter.name}
        <br />
        TYPE: {this.state.starter.types}
        <br />
        LEVEL: {this.state.starter.level}
        <br />
      </Button>
    )
  }
}