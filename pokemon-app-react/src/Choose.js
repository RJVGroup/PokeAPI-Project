import React, { Component } from 'react';
import './App.css'


import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';


export default class Choose extends Component {
  constructor(props) {
    super(props);
  this.state = {
      starter1: [],
      starter2: [],
      starter3: [],
     
}
}


componentDidMount() {
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
    return (

      <div className="choose-game">  
       
       <button className="menu main-game-panel"  style={{backgroundColor: 'rgb(11, 221, 57)'} }>
       <div className="pokemonimg">  
       <img src={"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+this.state.starter1.level+".png"}/>
       </div>  
            NAME: {this.state.starter1.name}
             <br/> 
            TYPE: {this.state.starter1.types}
        </button>
        <button className="menu main-game-panel"  style={{backgroundColor: 'rgb(11, 197, 221)'}}>
        <span className="pokemonimg">  
       <img src={"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+this.state.starter1.level+".png"}/>
       </span>  
            NAME: {this.state.starter2.name}
             <br/> 
             TYPE: {this.state.starter2.types}
              
          
            </button> 
          <button className="menu main-game-panel"  style={{backgroundColor: 'rgb(255, 123, 0'}}>
          <span className="pokemonimg">  
       <img src={"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+this.state.starter1.level+".png"}/>
       </span>  
             NAME: {this.state.starter3.name}
             <br/> 
             TYPE: {this.state.starter3.types}
          </button>   
      </div>
    );
  }
}