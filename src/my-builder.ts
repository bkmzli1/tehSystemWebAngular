import { BuilderContext, BuilderOutput, createBuilder } from '@angular-devkit/architect';
import { JsonObject } from '@angular-devkit/core';
import * as childProcess from 'child_process';

interface Options extends JsonObject {
  command: string;
  args: string[];
}

export default createBuilder(commandBuilder);

function commandBuilder(
  options: Options,
  context: BuilderContext,
): Promise<BuilderOutput> {
  const child = childProcess.spawn(options.command, options.args);
  return new Promise(resolve => {
    child.on('close', code => {
      resolve({ success: code === 0 });
    });
  });
}
