import { module, test } from 'qunit';
import { setupTest } from 'ember-qunit';

module('Unit | Controller | book-ticket', function(hooks) {
  setupTest(hooks);

  // Replace this with your real tests.
  test('it exists', function(assert) {
    let controller = this.owner.lookup('controller:book-ticket');
    assert.ok(controller);
  });
});
