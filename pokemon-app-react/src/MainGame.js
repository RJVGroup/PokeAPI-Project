import React, { Component } from 'react';
import './App.css'
import { Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button, FormGroup } from 'reactstrap';
import Choose from './Choose';
import Battle from './Battle';
import Buy from './BuyMenu';
import Roam from './Roam';
import Shop from './Shop';
import PokemonParty from './PokemonParty';





class MainGame extends Component {
  constructor(props) {
    super(props);
    this.chosenClick = this.chosenClick.bind(this);
    this.move=this.move.bind(this);
    this.run=this.run.bind(this);

    this.state = {
      cpokemon:this.props.cpokemon,
      epokemon: '',
      move:'',
      name: this.props.name,
      id: this.props.id,
      level: this.props.level,

      locationtext:'You set off to find pokémon and adventure!',
location:'',
prevLocation:''
    };
  }




  componentDidMount() {
this.initialLocation();
this.props.setStarter()
}
    initialLocation(){
      fetch('api/terrain/generate/5', { method: 'GET' })
    .then(response => response.json())
    .then(data => this.setState({
    location:data.name,
    }));
    }
    componentDidUpdate(prevProps) {
      if (this.props.cpokemon !== prevProps.cpokemon) {
        this.setState({cpokemon:this.props.cpokemon, name:this.props.cpokemon.name,id:this.props.cpokemon.id,level:this.props.cpokemon.level});
      }
    }
  
  chosenClick() {
    this.setState({ chosen: true })
  }

  move() {
    fetch('api/terrain/generate/5', { method: 'GET' })
    .then(response => response.json())
    .then(data => this.setState({ move:data.pokemonEncountered,
    location:data.name,
    }));
    if(this.state.location==this.state.prevLocation){this.setState({locationtext:'You arrived at a '+this.state.location+' again.'})}else{this.setState({locationtext:'You arrived at a '+this.state.location+'.', prevLocation:this.state.location})}
      if(this.state.move.enemyMon==null){this.setState({epokemon:''})}else{this.setState({epokemon:this.state.move.enemyMon})}
  }
  run(){
    this.setState({epokemon:''})
   }
 
   
  render() {
    const cpokemon = this.state.cpokemon;
    const epokemon = this.state.epokemon;
    const locationtext = this.state.locationtext;


if (epokemon != '') {
      return (
        <div className='App'>
          <Battle change={this.props.change} location={locationtext} epokemon={epokemon} cpokemon={cpokemon} close={this.run} />
        </div>)
    } 
   else return (
        <div className="App">
          <Roam change={this.props.change} name={this.state.name} id={this.state.id} level={this.state.level} move={this.move} location={locationtext} cpokemon={cpokemon} />
        </div>
      ); 
  }
}

export default MainGame;
