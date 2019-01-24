import React, { Component } from 'react';
import './App.css'
import FrontImg from './FrontImg';


export default class StarterPokemon extends Component {
    constructor(props) {
        super(props);
        this.chooseStarter=this.chooseStarter.bind(this);
      this.state = {
          starter: [],
         
    }
    }
    
    
    componentDidMount() {
      fetch('api/pokemon/5/'+this.props.pokemon,{method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({starter:data}));
        this.setState({bag:this.props.pokemon})
    }
       
       chooseStarter() {
        fetch('/api/player/choose-starter/'+this.props.pokemon
        ,{
          method: 'POST',
        });
        }
    
      render() {
        return (
           <button className="menu main-game-panel" onClick={this.chooseStarter} style={this.props.pokestyle}>
              <FrontImg id={this.state.starter.id}/>
                NAME: {this.state.starter.name}
                 <br/> 
                TYPE: {this.state.starter.types}
                <br/>
                LEVEL: {this.state.starter.level}
                <br/>
            </button>
        )
      }
    }