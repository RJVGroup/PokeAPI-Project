import React, { Component } from "react";
import "./App.css";


import {

  Container,

} from "reactstrap";

export default class Shop extends Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }


  render() {
    return (
      <div className="col-game">
        <Container className="menu main-game-panel">
          <button className=" main-game-panel" onClick={this.props.buyMenuToggle}>
            Buy
          </button>
          <button className=" main-game-panel" onClick={this.props.sellMenuToggle}>
            Sell
          </button>
          <button className=" main-game-panel" onClick={this.props.shopToggle}>
            Leave
          </button>
        </Container>
      </div>
    );
  }
}
