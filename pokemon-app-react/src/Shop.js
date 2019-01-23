import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';

export default class Shop extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            shopstock: []
        }
    }


    componentDidMount() {
        const shop =fetch('api/shop/generate',{method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({shopstock:data.shopInventory}))
    }
    generateshop =()=>{
         let pos = 0;
         let shopstock=this.state.shopstock;
        var test = []
       

        shopstock.forEach(function(arrayItem,arrayIndex,array){
            pos=pos++;
            test.push(
                    
                <div>
                  <button>  {array[arrayIndex].itemName}</button></div>
                    
                
           )})
        return test;
    }  

    render() {
        return (
                <div className='main-game'>  
         <Container className="menu main-game-panel">
       {this.generateshop()}
             </Container> 
        </div>  
        )            
        }
}
