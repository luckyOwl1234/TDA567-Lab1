// Dafny 2.0.0.00922 technical preview 0
// Command Line Options: Tokeneer/Tokeneer.dfy
// Tokeneer.dfy

class EnrollmentStation {
  var users: set<int>

  method init()
    modifies this`users
  {
    users := {};
  }

  method enroll(fingerprint: int, clearance: int) returns (token: Token)
    decreases fingerprint, clearance
  {
  }
  }

  class Token {
  var id: int
  var clearance: int
  var fingerprint: int
  var invalidated: bool

  method init(id: int, clearance: int, fingerprint: int)
    requires 0 <= id
    requires 1 <= clearance <= 3
    requires 0 <= fingerprint
    modifies this`id, this`clearance, this`fingerprint, this`invalidated
    ensures 0 <= id
    ensures 1 <= clearance <= 3
    ensures invalidated == false;
    decreases id, clearance
  {
    this.id := id;
    this.clearance := clearance;
    this.fingerprint := fingerprint;
    this.invalidated := false;
  }

  method setClearance(clearance: int) returns (clearanceSet: bool)
    requires 0 <= clearance <= 3
    modifies this`clearance
    ensures 0 <= clearance <= 3
    decreases clearance
  {
    this.clearance := clearance;
    if this.clearance == clearance {
      clearanceSet := true;
    } else {
      clearanceSet := false;
    }
  }

  method invalidate() //returns (invalidated: bool)
    requires clearance != 0
    modifies this`clearance, this`invalidated
    ensures clearance == 0
  {
    if this.clearance != 0 {
      clearance := 0;
      invalidated := true;
    } else {
      invalidated := false;
    }
  }

  method getClearance() returns (clearance: int)
  {
    return this.clearance;
  }
}

class IDStation {
  var doorOpen: bool
  var doorClearance: int
  var alarmActive: bool

  method init(doorClearance: int)
    requires 1 <= doorClearance <= 3
    modifies this`doorOpen, this`doorClearance, this`alarmActive
    ensures this.doorOpen == false
    ensures this.doorClearance == doorClearance
    ensures this.alarmActive == false
  {
    doorOpen := false;
    this.doorClearance := doorClearance;
    alarmActive := false;
  }

  method tryOpen(token: Token, fingerprint: int) returns (doorOpen: bool)
    requires doorOpen == false
    requires alarmActive == false
    requires token != null
    requires 0 <= fingerprint
    requires 0 <= token.fingerprint
    requires 1 <= token.clearance <= 3
    modifies this`doorOpen, this`alarmActive, token
  {
    // Verify user integrity
    if token.fingerprint == fingerprint { //this.verifyUser(token, fingerprint) && this.verifyClearance(token) {
      // Verify clearance
      if token.clearance >= this.doorClearance {
        // Open door
        open();
      } else {
        // ???? BAD CLEARANCE
      }
    } else {
      alarmOn();
      token.invalidate();
    }
  }

    /*
    method verifyUser(token: Token, fingerprint: int) returns (correctUser: bool)
    requires 0 <= token.fingerprint
    requires 0 <= fingerprint
    requires token != null
    requires this.alarmActive == false
    modifies this`alarmActive, token
    decreases fingerprint
  {
    if token.fingerprint == fingerprint {
      correctUser := true;
      //verifyClearance(token);
    } else {
      correctUser := false;
      alarmOn();
      token.invalidate();
    }
  }

  method verifyClearance(token: Token) returns (correctClearance: bool)
    requires 1 <= token.clearance
    decreases token
  {
    if token.clearance >= this.doorClearance {
      correctClearance := true;
    } else {
      correctClearance := false;
    }
  } */

  method alarmOn()
    requires this.alarmActive == false
    modifies this`alarmActive
    ensures this.alarmActive == true
  {
    this.alarmActive := true;
  }

  method alarmOff()
    requires this.alarmActive == true
    modifies this`alarmActive
    ensures this.alarmActive == false
  {
    this.alarmActive := false;
  }

  method open() //returns (doorOpen: bool)
    requires doorOpen == false
    modifies this`doorOpen
    ensures this.doorOpen == true
  {
    this.doorOpen := true;
  }

  method close() //returns (doorOpen: bool)
    requires doorOpen == true
    modifies this`doorOpen
    ensures this.doorOpen == false
  {
    this.doorOpen := false;
  }
}

