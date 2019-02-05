import React, { Component } from 'react';
import './App.css'
import FrontImg from './FrontImg';
import StarterPokemon from './StarterPokemon';
import Roam from './Roam';


import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class Choose extends Component {
  constructor(props) {
    super(props);

  this.state = {
    starter1:[],
    starter2:[],
    starter3:[],

   
}
}
componentWillMount() {
  Promise.all([fetch('api/pokemon/5/bulbasaur',{method: 'GET'}),
  fetch('api/pokemon/5/squirtle',{method: 'GET'}),
  fetch('api/pokemon/5/charmander',{method: 'GET'})
  ])
  .then(([res1, res2, res3]) => Promise.all([res1.json(), res2.json(), res3.json()]))
  .then(([data1, data2, data3]) => this.setState({
        starter1: data1,
        starter2: data2,
        starter3: data3
    }));
   } 


  render() {
    return(
      <div className="col-game">
      <div className="row-game"onClick={this.props.close}>  
       
      <StarterPokemon pokemon={this.state.starter1} pokestyle={{backgroundColor: 'rgb(11, 221, 57)'} }/>
  
      <StarterPokemon pokemon={this.state.starter2}  pokestyle={{backgroundColor: 'rgb(11, 197, 221)'}}/>
        
      <StarterPokemon pokemon={this.state.starter3} pokestyle={{backgroundColor: 'rgb(255, 123, 0'}}/>
            </div>

      <Container className= "menu main-game-panel">
      <h1>Choose your starter Pokémon!!!</h1>
        </Container>
      </div>
    );}
  
  
}