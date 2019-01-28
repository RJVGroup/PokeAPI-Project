import React, { Component } from 'react';
import './App.css'
import { Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button, FormGroup } from 'reactstrap';
import Choose from './Choose';
import Battle from './Battle';
import Buy from './BuyMenu';
import Roam from './Roam';
import Shop from './Shop';
import PokemonParty from './PokemonParty';
import MainGame from './MainGame';





class App extends Component {
  constructor(props) {
    super(props);
    this.chosenClick = this.chosenClick.bind(this);
    this.changePokemon=this.changePokemon.bind(this);
    this.setStarter=this.setStarter.bind(this);


    this.state = {
      chosen: false,
      party: [],
      cpokemon: '',
      name:'',
      id:'',
      level:'',
      locationtext:'You set off to find pokémon and adventure!',
location:'',
prevLocation:''
    };
  }




  componentDidMount() {
    this.setStarter()
    
    }
  
  chosenClick() {
    this.setState({ chosen: true })
  }
  setStarter(){
      fetch('api/player/show-party',{method: 'GET'})
  .then(response => response.json())
  .then(data=>this.setState({party: data,cpokemon: data[0]}))
}
  changePokemon(name, level){
    fetch('api/pokemon/'+level+'/'+name,{method: 'GET'})
    .then(response => response.json())
    .then(data=>this.setState({cpokemon:data}));
   }
  render() {
    const chosen = this.state.chosen;
    const party = this.state.party;
    const cpokemon = this.state.cpokemon;





    if (chosen == false && party == '') {
      return (
        <div className="App">
          <Choose close={this.chosenClick} />
        </div>

      );
    }
   else return (
        <div className="App">
          <MainGame setStarter={this.setStarter} cpokemon={cpokemon} name={this.state.name} id={this.state.id} level={this.state.level}change={this.changePokemon}/>
        </div>
      ); 
  }
}

export default App;
