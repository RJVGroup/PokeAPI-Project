import React, { Component } from 'react';
import './App.css'
import FrontImg from './FrontImg';


export default class ViewItem extends Component {
    constructor(props) {
        super(props);
        this.chooseStarter=this.chooseStarter.bind(this);
      this.state = {
          description:this.props.itemDescription,
         
    }
    }
    
    buyClick(){
        this.setState({items:this.state.description})
    }
    
      render() {
        return (
           <button  onClick={this.chooseStarter}>
              View
            </button>
        )
      }
    }